package com.minh.contacts.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class ContactRequest {

    private String firstName;

    private String lastName;

    private String fullName;

    private String address;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Mobile phone number is mandatory")
    @Pattern(regexp = "(\\+84|0)[0-9]{9}")
    private String mobilePhoneNumber;

    private Set<Long> skillIds = new HashSet<>();
}
