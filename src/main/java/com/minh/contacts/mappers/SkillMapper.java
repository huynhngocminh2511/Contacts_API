package com.minh.contacts.mappers;

import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.SkillRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    Skill mapToSkill(SkillRequest skillRequest);
}
