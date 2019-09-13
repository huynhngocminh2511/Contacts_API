package com.minh.contacts.models.responses;

import com.minh.contacts.models.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ContactResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String address;

    private String email;

    private String mobilePhoneNumber;

    private Set<SkillResponse> skills = new HashSet<>();

    private Set<Role> roles = new HashSet<>();
}
