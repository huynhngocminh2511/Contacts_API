package com.minh.contacts.configurations;

import com.minh.contacts.models.Contact;
import com.minh.contacts.models.Role;
import com.minh.contacts.models.enums.RoleEnum;
import com.minh.contacts.repositories.ContactRepository;
import com.minh.contacts.repositories.RoleRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Configuration
@Profile({"!test"})
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${jwt-key}")
    private String signingKey;

    private void addRoleIfMissing(String name){
        if (roleRepository.findByName(name) == null) {
            roleRepository.save(new Role(name));
        }
    }

    private void addUserIfMissing(String username, String password, String... roles){
        if (!contactRepository.findByEmail(username).isPresent()) {
            Contact contact = new Contact();

            contact.setEmail(username);
            contact.setPassword(new BCryptPasswordEncoder().encode(password));

            contact.setRoles(new HashSet<>());

            for (String role: roles) {
                contact.getRoles().add(roleRepository.findByName(role));
            }

            contactRepository.save(contact);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        addRoleIfMissing(RoleEnum.USER.getName());
        addRoleIfMissing(RoleEnum.ADMIN.getName());

        addUserIfMissing("user@gmail.com", "user", RoleEnum.USER.getName());
        addUserIfMissing("admin@gmail.com", "admin", RoleEnum.USER.getName(), RoleEnum.ADMIN.getName());

        if(signingKey == null || signingKey.length() ==0){
            String jws = Jwts.builder()
                    .setSubject("Blog")
                    .signWith(SignatureAlgorithm.HS256, "ContactApi").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }
    }
}