package com.minh.contacts.controllers;

import com.minh.contacts.exceptions.EntityNotFoundException;
import com.minh.contacts.models.Contact;
import com.minh.contacts.models.requests.ContactRequest;
import com.minh.contacts.models.requests.ContactUpdateRequest;
import com.minh.contacts.models.responses.ContactResponse;
import com.minh.contacts.repositories.ContactRepository;
import com.minh.contacts.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public List<ContactResponse> getAll() {
        return contactService.getAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
    public ContactResponse get(@PathVariable Long id) {
        return contactService.get(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping()
    public ContactResponse create(@Valid @RequestBody ContactRequest contactRequest) {
        return contactService.create(contactRequest);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/{id}")
    public ContactResponse update(@PathVariable Long id, @Valid @RequestBody ContactUpdateRequest contactUpdateRequest) {
        return contactService.update(id, contactUpdateRequest);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }
}
