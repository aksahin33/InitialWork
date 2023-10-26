package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.domain.ContactMessage;
import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.dto.UpdateBodyDTO;
import com.techproeducation.backendproject.initialwork.exceptions.ResourceNotFoundException;
import com.techproeducation.backendproject.initialwork.exceptions.TimeFormatException;
import com.techproeducation.backendproject.initialwork.mapper.DTOtoDomain;
import com.techproeducation.backendproject.initialwork.mapper.DomainToDTO;
import com.techproeducation.backendproject.initialwork.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;



    public void createMessage(ContactMessageDTO contactMessageDto) {

        ContactMessage contactMessage = DTOtoDomain.dtoToDomain(contactMessageDto);

        contactMessageRepository.save(contactMessage);
    }

    public ContactMessage getMessageById(Long id) {

        ContactMessage contactMessage = contactMessageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Message with id "+id+" cannot be found."));

        return contactMessage;
    }

    public List<ContactMessageDTO> findAllMessages() {

        List<ContactMessage> contactMessageList = contactMessageRepository.findAll();
        if(contactMessageList.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages.");
        }

       return DomainToDTO.convertToDTOList(contactMessageList);


    }

    public List<ContactMessageDTO> getAllMessagesWithPage(Pageable pageable) {

        Page<ContactMessage> contactMessagePage = contactMessageRepository.findAll(pageable);

        if(contactMessagePage.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages.");
        }

        List<ContactMessage> contactMessageList = contactMessagePage.getContent();

        return DomainToDTO.convertToDTOList(contactMessageList);


    }

    public List<ContactMessageDTO> findContactMessageByEmail(String email) {

        List<ContactMessage> contactMessageList = contactMessageRepository.findByEmail(email);

        if(contactMessageList.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages.");
        }

        return DomainToDTO.convertToDTOList(contactMessageList);

    }

    public void deleteMessage(Long id) {

        ContactMessage contactMessage = contactMessageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Message with id "+id+" does not exist."));

        contactMessageRepository.delete(contactMessage);

    }

    public void updateMessage(Long id, UpdateBodyDTO updateBodyDTO) {

        ContactMessage contactMessage = getMessageById(id);
        
        if(updateBodyDTO.getMessage()!=null){
            contactMessage.setMessage(updateBodyDTO.getMessage());
        }
        if(updateBodyDTO.getName()!=null){
            contactMessage.setName(updateBodyDTO.getName());
        }
        if(updateBodyDTO.getSubject()!=null){
            contactMessage.setSubject(updateBodyDTO.getSubject());
        }
        if (updateBodyDTO.getEmail()!=null){
            contactMessage.setEmail(updateBodyDTO.getEmail());
        }
        
        contactMessageRepository.save(contactMessage);

    }

    public List<ContactMessageDTO> searchBySubject(String subject) {

        List<ContactMessage> contactMessageList = contactMessageRepository.findBySearchString(subject);

        if(contactMessageList.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages.");
        }

        return DomainToDTO.convertToDTOList(contactMessageList);
    }

    public List<ContactMessageDTO> getMessagesBetweenDates(String startDateStr, String endDateStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime startDate;
        LocalDateTime endDate;


        try {
            startDate = LocalDate.parse(startDateStr, formatter).atStartOfDay();
            endDate = LocalDate.parse(endDateStr, formatter).atTime(23,59,59);
        }catch(DateTimeParseException e){
            throw new TimeFormatException("Date format is not correct! Should be 'dd.MM.yyyy'");
        }

        List<ContactMessage> contactMessageList = contactMessageRepository.findMessagesBetweenDates(startDate,endDate);

        if(contactMessageList.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages between the given dates.");
        }

        return DomainToDTO.convertToDTOList(contactMessageList);

    }


    public List<ContactMessageDTO> getMessagesBetweenTimes(String startTimeStr, String endTimeStr) {

        List<ContactMessage> contactMessageList = contactMessageRepository.findAll();
        if(contactMessageList.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime;
        LocalTime endTime;
        try {
            startTime = LocalTime.parse(startTimeStr, formatter).withSecond(0);
            endTime = LocalTime.parse(endTimeStr, formatter).withSecond(0);
            System.out.println(startTime);
            System.out.println(endTime);
        }catch(DateTimeParseException e){
            throw new TimeFormatException("Time format is not correct! Should be 'HH:mm'");
        }

        List<ContactMessage> messagesBetweenTimes = new ArrayList<>();
        for(ContactMessage x : contactMessageList){

            System.out.println(x.getMessage());
            LocalTime creationTime = LocalTime.from(x.getCreationDateTime());

            if(creationTime.isAfter(startTime)&&creationTime.isBefore(endTime)){
                messagesBetweenTimes.add(x);
            }
        }


        if(messagesBetweenTimes.isEmpty()){
            throw new ResourceNotFoundException("There are no contact messages between the given times.");
        }

        return DomainToDTO.convertToDTOList(messagesBetweenTimes);
    }
}
