package com.minh.contacts.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class SkillRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Level is mandatory")
    private String level;
}
