package com.minh.contacts.controllers;

import com.minh.contacts.exceptions.EntityNotFoundException;
import com.minh.contacts.models.Contact;
import com.minh.contacts.models.requests.ContactRequest;
import com.minh.contacts.repositories.ContactRepository;
import com.minh.contacts.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAll() {
        return contactService.getAll();
    }

    @GetMapping("/{id}")
    public Contact get(@PathVariable Long id) {
        return contactService.get(id);
    }

    @PostMapping()
    public Contact create(@Valid @RequestBody ContactRequest contactRequest) {
        return contactService.create(contactRequest);
    }

    @PutMapping("/{id}")
    public Contact update(@PathVariable Long id, @Valid @RequestBody ContactRequest contactRequest) {
        return contactService.update(id, contactRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }
}
