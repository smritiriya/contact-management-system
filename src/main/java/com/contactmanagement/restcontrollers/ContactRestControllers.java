package com.contactmanagement.restcontrollers;

import com.contactmanagement.entity.Contact;
import com.contactmanagement.entity.ExceptionResponse;
import com.contactmanagement.serivice.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContactRestControllers {

    @Autowired
    private ContactService contactService;

    @GetMapping("/getContacts")
    @Operation(summary = "Get all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all contacts",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactService.getContacts();
        ResponseEntity<List<Contact>> response = new ResponseEntity<>(contacts, HttpStatus.OK);
        return response;
    }

    @Operation(summary = "Get a contact by its mobile number as Query parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the contact",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contact not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @GetMapping("/getContact")
    public ResponseEntity<Contact> getContact(@Parameter(description = "mobile number to be searched") @RequestParam String mobileNumber) {
        Contact contact = contactService.getContact(mobileNumber);
        ResponseEntity<Contact> response = new ResponseEntity<>(contact, HttpStatus.OK);
        return response;
    }

    @Operation(summary = "Add a contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Contact already exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @PostMapping("/saveContact")
    public ResponseEntity<Contact> saveContact(@RequestBody @Valid Contact contact) {
        Contact savedContact = contactService.saveContact(contact);
        ResponseEntity<Contact> response = new ResponseEntity<>(savedContact, HttpStatus.CREATED);
        return response;
    }

    @Operation(summary = "Add list of contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "List of contact added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Some contact already exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @PostMapping("/saveContacts")
    public ResponseEntity<List<Contact>> saveContacts(@RequestBody @Valid List<Contact> contacts) {
        List<Contact> savedContacts = contactService.saveContacts(contacts);
        ResponseEntity<List<Contact>> response = new ResponseEntity<>(savedContacts, HttpStatus.CREATED);
        return response;
    }

    @Operation(summary = "Update a contact by its mobile number as specified in URI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Update a contact",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contact not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @PutMapping("/updateContact/{mobileNumber}")
    public ResponseEntity<Contact> updateContact(@Parameter(description = "mobile number to be searched") @PathVariable(name = "mobileNumber") String mobileNumber, @RequestBody @Valid Contact updatedContact) {
        Contact newUpdatedContact = contactService.updateContact(mobileNumber, updatedContact);
        ResponseEntity<Contact> response = new ResponseEntity<>(newUpdatedContact, HttpStatus.ACCEPTED);
        return response;
    }

    @Operation(summary = "Delete a contact by its mobile number as specified in URI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete a contact",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @DeleteMapping("/deleteContact/{mobileNumber}")
    public ResponseEntity deleteContact(@Parameter(description = "mobile number to be searched") @PathVariable(name = "mobileNumber") String mobileNumber) {
        contactService.deleteContact(mobileNumber);
        ResponseEntity response = new ResponseEntity(HttpStatus.NO_CONTENT);
        return response;
    }
}
