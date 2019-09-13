package com.minh.contacts.repositories;

import com.minh.contacts.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByEmail(String name);

    Boolean existsByEmail(String email);
}
