package com.minh.contacts.services;

import com.minh.contacts.exceptions.EntityNotFoundException;
import com.minh.contacts.mappers.ContactMapper;
import com.minh.contacts.models.Contact;
import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.ContactRequest;
import com.minh.contacts.repositories.ContactRepository;
import com.minh.contacts.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ContactMapper contactMapper;

    private Set<Skill> mapToSkill(Set<Long> ids) {
        return ids.stream()
                .map(id -> skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill is not found")))
                .collect(Collectors.toSet());
    }

    private Contact getById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contact is not found"));
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    public Contact get(Long id) {
        return getById(id);
    }

    public Contact create(ContactRequest contactRequest) {
        Contact contact = contactMapper.mapToContact(contactRequest);

        Set<Skill> skills = mapToSkill(contactRequest.getSkillIds());

        contact.setSkills(skills);

        return contactRepository.save(contact);
    }

    public Contact update(Long id, ContactRequest contactRequest) {
        get(id);

        Contact contact = contactMapper.mapToContact(contactRequest);

        contact.setId(id);

        Set<Skill> skills = mapToSkill(contactRequest.getSkillIds());

        contact.setSkills(skills);

        return contactRepository.save(contact);
    }

    public void delete(Long id){
        getById(id);

        contactRepository.deleteById(id);
    }
}
