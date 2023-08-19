package com.contactmanagement.repository;

import com.contactmanagement.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);
    boolean existsByMobileNumber(String mobileNumber);
}
