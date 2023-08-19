package com.contactmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    @NotBlank(message = "Enter your firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Pattern(regexp="([0-9]{10})", message = "Enter 10 digit mobile number")
    @NotBlank(message = "Mobile number cannot be blank")
    @Column(name = "mobile_number", unique = true, nullable = false)
    private String mobileNumber;

    @Email(message = "Enter the email")
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

}
