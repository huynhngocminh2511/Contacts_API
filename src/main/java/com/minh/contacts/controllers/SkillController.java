package com.minh.contacts.controllers;

import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.SkillRequest;
import com.minh.contacts.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<Skill> getAll() {
        return skillService.getAll();
    }

    @GetMapping("/{id}")
    public Skill get(@PathVariable Long id) {
        return skillService.get(id);
    }

    @PostMapping
    public Skill create(@RequestBody SkillRequest skillRequest) {
        return skillService.create(skillRequest);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody SkillRequest skillRequest) {
        return skillService.update(id, skillRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        skillService.delete(id);
    }
}
