package com.minh.contacts.mappers;

import com.minh.contacts.models.Contact;
import com.minh.contacts.models.requests.ContactRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact mapToContact(ContactRequest contactRequest);
}
