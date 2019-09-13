package com.minh.contacts.mappers;

import com.minh.contacts.models.Contact;
import com.minh.contacts.models.requests.ContactRequest;
import com.minh.contacts.models.responses.ContactResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact mapToContact(ContactRequest contactRequest);

    ContactResponse mapToContactResponse(Contact contact);
}
