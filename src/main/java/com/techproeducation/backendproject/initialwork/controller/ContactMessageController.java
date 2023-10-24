package com.techproeducation.backendproject.initialwork.controller;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;


    @PostMapping    //http://localhost:8000/contact
    public ResponseEntity<String> createMessage(@Valid @RequestBody ContactMessage contactMessage){

        contactMessageService.createMessage(contactMessage);

        return new ResponseEntity<>("Message saved.", HttpStatus.CREATED);
    }



}
