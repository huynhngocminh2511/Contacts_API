package com.minh.contacts.services;

import com.minh.contacts.exceptions.EntityNotFoundException;
import com.minh.contacts.mappers.SkillMapper;
import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.SkillRequest;
import com.minh.contacts.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    private Skill getById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill is not found"));
    }

    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    public Skill get(Long id) {
        return getById(id);
    }

    public Skill create(SkillRequest skillRequest) {
        Skill skill = skillMapper.mapToSkill(skillRequest);

        return skillRepository.save(skill);
    }

    public Skill update(Long id, SkillRequest skillRequest) {
        getById(id);

        Skill skill = skillMapper.mapToSkill(skillRequest);

        skill.setId(id);

        return skillRepository.save(skill);
    }

    public void delete(Long id) {
        getById(id);

        skillRepository.deleteById(id);
    }
}

