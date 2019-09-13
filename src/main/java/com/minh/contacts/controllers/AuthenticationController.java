package com.minh.contacts.controllers;

import com.minh.contacts.models.requests.ContactRequest;
import com.minh.contacts.models.requests.LoginRequest;
import com.minh.contacts.models.responses.ContactResponse;
import com.minh.contacts.models.responses.TokenResponse;
import com.minh.contacts.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private ContactService contactService;

    @PostMapping("login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return contactService.login(loginRequest);
    }

    @PostMapping("sign-up")
    public ContactResponse signUp(@Valid @RequestBody ContactRequest contactRequest) {
        return contactService.create(contactRequest);
    }
}