package com.minh.contacts.services;

import com.minh.contacts.configurations.TokenProvider;
import com.minh.contacts.exceptions.EmailExistedException;
import com.minh.contacts.exceptions.EntityNotFoundException;
import com.minh.contacts.mappers.ContactMapper;
import com.minh.contacts.mappers.SkillMapper;
import com.minh.contacts.models.Contact;
import com.minh.contacts.models.Skill;
import com.minh.contacts.models.requests.ContactRequest;
import com.minh.contacts.models.requests.LoginRequest;
import com.minh.contacts.models.responses.ContactResponse;
import com.minh.contacts.models.responses.TokenResponse;
import com.minh.contacts.repositories.ContactRepository;
import com.minh.contacts.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    private Set<Skill> mapToSkill(Set<Long> ids) {
        return ids.stream()
                .map(id -> skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill is not found")))
                .collect(Collectors.toSet());
    }

    private ContactResponse mapToContactResponse(Contact contact) {
        ContactResponse contactResponse = contactMapper.mapToContactResponse(contact);
        contactResponse.setSkills(skillMapper.mapToSkills(contact.getSkills()));

        return contactResponse;
    }

    private Contact getById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contact is not found"));
    }

    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setToken(jwtTokenUtil.generateToken(authentication));

        return tokenResponse;
    }

    public List<ContactResponse> getAll() {
        return contactRepository.findAll().stream()
                .map(this::mapToContactResponse)
                .collect(Collectors.toList());
    }

    public ContactResponse get(Long id) {
        return mapToContactResponse(getById(id));
    }

    public ContactResponse create(ContactRequest contactRequest) {
        if (contactRepository.findByEmail(contactRequest.getEmail()).isPresent()) {
            throw new EmailExistedException("Email is existed");
        }

        Contact contact = contactMapper.mapToContact(contactRequest);

        contact.setPassword(new BCryptPasswordEncoder().encode(contactRequest.getPassword()));

        Set<Skill> skills = mapToSkill(contactRequest.getSkillIds());

        contact.setSkills(skills);

        return mapToContactResponse(contactRepository.save(contact));
    }

    public ContactResponse update(Long id, ContactRequest contactRequest) {
        getById(id);

        Contact contact = contactMapper.mapToContact(contactRequest);

        contact.setId(id);

        Set<Skill> skills = mapToSkill(contactRequest.getSkillIds());

        contact.setSkills(skills);

        return mapToContactResponse(contactRepository.save(contact));
    }

    public void delete(Long id){
        getById(id);

        contactRepository.deleteById(id);
    }
}
