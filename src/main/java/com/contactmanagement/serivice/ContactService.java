package com.contactmanagement.serivice;

import com.contactmanagement.customexceptions.ContactNotFound;
import com.contactmanagement.entity.Contact;
import com.contactmanagement.repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

    public Contact getContact(String mobileNumber) {
        Contact contact = contactRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ContactNotFound("contact with " + mobileNumber + " does not exist"));
        return contact;
    }

    public Contact saveContact(Contact contact) {
        Contact savedContact;
//        try{
        savedContact = contactRepository.save(contact);
//        }catch (DataIntegrityViolationException de){
//            de.printStackTrace();
//            throw new ContactAlreadyExist("contact with " + contact.getMobileNumber() + " already exist");
//        }
        return savedContact;
    }

    public List<Contact> saveContacts(List<Contact> contacts) {
//        List<Contact> savedContacts = new ArrayList<>();
//        StringBuilder stringBuilder = new StringBuilder("");
//        for(Contact contact : contacts){
//            if(contactRepository.existsByMobileNumber(contact.getMobileNumber())){
//                stringBuilder.append(contact.getMobileNumber());
//                continue;
//            }
////            Contact savedContact = contactRepository.save(contact);
//            savedContacts.add(contact);
//        }
        List<Contact> savedContacts;
//        try{
        savedContacts = contactRepository.saveAll(contacts);
//        } catch (Exception e){
//            throw new ContactAlreadyExist("Some contact already exists. Please change your numbers and then add to save all");
//        }
        return savedContacts;
    }

    public Contact updateContact(String mobileNumber, Contact updatedContact) {
        Contact contact = contactRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ContactNotFound("contact with " + mobileNumber + " does not exist"));
        updatedContact.setId(contact.getId());
        Contact newUpadatedContact = contactRepository.save(updatedContact);
        return newUpadatedContact;
    }

    @Transactional
    public void deleteContact(String mobileNumber) {
        contactRepository.deleteByMobileNumber(mobileNumber);
    }
}
