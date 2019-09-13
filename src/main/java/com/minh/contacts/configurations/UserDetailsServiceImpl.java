package com.minh.contacts.configurations;

import com.minh.contacts.exceptions.EntityNotFoundException;
import com.minh.contacts.models.Contact;
import com.minh.contacts.models.Role;
import com.minh.contacts.repositories.ContactRepository;
import com.minh.contacts.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Contact contact = contactRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Contact is not found"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Set<Role> roles = contact.getRoles();

        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                contact.getEmail(), contact.getPassword(), grantedAuthorities);
    }
}
