package com.minh.contacts.mappers;

import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.SkillRequest;
import com.minh.contacts.models.responses.SkillResponse;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    Skill mapToSkill(SkillRequest skillRequest);

    Set<SkillResponse> mapToSkills(Set<Skill> skills);
}
