package com.minh.contacts.controllers;

import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.SkillRequest;
import com.minh.contacts.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public List<Skill> getAll() {
        return skillService.getAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
    public Skill get(@PathVariable Long id) {
        return skillService.get(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public Skill create(@Valid @RequestBody SkillRequest skillRequest) {
        return skillService.create(skillRequest);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @Valid @RequestBody SkillRequest skillRequest) {
        return skillService.update(id, skillRequest);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        skillService.delete(id);
    }
}
