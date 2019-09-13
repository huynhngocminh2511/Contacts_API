package com.minh.contacts.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String name;
}
