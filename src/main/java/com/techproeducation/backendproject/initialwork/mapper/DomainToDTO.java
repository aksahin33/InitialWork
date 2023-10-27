package com.techproeducation.backendproject.initialwork.mapper;

import com.techproeducation.backendproject.initialwork.domain.ContactMessage;
import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;

import java.util.ArrayList;
import java.util.List;

public class DomainToDTO {

    //private ContactMessageDTO contactMessageDTO = new ContactMessageDTO();


    //Converting ContactMessage object to ContactMessageDTO object
    public static ContactMessageDTO domainToDto(ContactMessage contactMessage1){

        ContactMessageDTO contactMessageDTO = new ContactMessageDTO();

        contactMessageDTO.setName(contactMessage1.getName());
        contactMessageDTO.setSubject(contactMessage1.getSubject());
        contactMessageDTO.setEmail(contactMessage1.getEmail());
        contactMessageDTO.setMessage(contactMessage1.getMessage());

        return contactMessageDTO;
    }

    //Converting list of ContactMessage to List of ContactMessageDTO
    public static List<ContactMessageDTO> convertToDTOList(List<ContactMessage> contactMessages) {
        List<ContactMessageDTO> dtoList = new ArrayList<>();
        for (ContactMessage message : contactMessages) {
            dtoList.add(domainToDto(message));
        }
        return dtoList;
    }

}
