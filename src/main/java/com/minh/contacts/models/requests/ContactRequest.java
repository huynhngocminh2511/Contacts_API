package com.minh.contacts.models.requests;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ContactRequest {

    private String firstName;

    private String lastName;

    private String fullName;

    private String address;

    private String email;

    private String mobilePhoneNumber;

    private Set<Long> skillIds = new HashSet<>();
}
