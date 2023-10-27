package com.techproeducation.backendproject.initialwork.mapper;

import com.techproeducation.backendproject.initialwork.domain.ContactMessage;
import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;

public class DTOtoDomain {


    //Converting DTO object to domain object
    public static ContactMessage dtoToDomain(ContactMessageDTO contactMessageDTO){

        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(contactMessageDTO.getName());
        contactMessage.setMessage(contactMessageDTO.getMessage());
        contactMessage.setSubject(contactMessageDTO.getSubject());
        contactMessage.setEmail(contactMessageDTO.getEmail());
        return contactMessage;
    }


}
