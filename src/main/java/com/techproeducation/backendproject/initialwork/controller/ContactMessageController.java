package com.techproeducation.backendproject.initialwork.controller;

import com.techproeducation.backendproject.initialwork.domain.ContactMessage;
import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.dto.UpdateBodyDTO;
import com.techproeducation.backendproject.initialwork.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contact")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;


    @PostMapping    //http://localhost:8000/contact   +  POST
    public ResponseEntity<String> createMessage(@Valid @RequestBody ContactMessageDTO contactMessage){

        contactMessageService.createMessage(contactMessage);

        return new ResponseEntity<>("Message saved.", HttpStatus.CREATED);
    }


    @GetMapping       //http://localhost:8000/contact   +  GET
    public ResponseEntity<List<ContactMessageDTO>> getAllMessages(){

        List<ContactMessageDTO> contactMessageDTOS = contactMessageService.findAllMessages();

        return new ResponseEntity<>(contactMessageDTOS,HttpStatus.OK);

    }

    @GetMapping("/pagination")   //http://localhost:8000/contact/pagination?page=1&size=10&sort=name&direction=ASC   +  GET
    public ResponseEntity<List<ContactMessageDTO>> getAllMessagesWithPagination(
            @RequestParam("page") int page, //page number
            @RequestParam("size") int size, //items per page
            @RequestParam("sort") String sort,  //sort based on a data (optional)
            @RequestParam("direction") Sort.Direction direction //Ascending or Descending order(ASC or DESC)
    ){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(direction,sort));
        List<ContactMessageDTO> contactMessageDTOPage = contactMessageService.getAllMessagesWithPage(pageable);

        return new ResponseEntity<>(contactMessageDTOPage, HttpStatus.OK);

    }

    @GetMapping("/search")       //http://localhost:8000/contact/search?email=a@a.com
    public ResponseEntity<List<ContactMessageDTO>> getMessagesByEmail(@RequestParam("email") String email){

        List<ContactMessageDTO> contactMessageDTOList = contactMessageService.findContactMessageByEmail(email);

        return ResponseEntity.ok(contactMessageDTOList);

    }



    @DeleteMapping("/{id}")       //http://localhost:8000/contact/2
    public ResponseEntity<String> deleteMessageById(@PathVariable Long id){
        contactMessageService.deleteMessage(id);

        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }


    @DeleteMapping("/delete")    //http://localhost:8000/contact/delete?id=2
    public ResponseEntity<String>  deleteMessageByParamId(@RequestParam("id") Long id){
        contactMessageService.deleteMessage(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }


    @PutMapping ("/{id}")  //  //http://localhost:8000/contact/2  +  PUT
    public ResponseEntity<String> updateMessage(@PathVariable Long id, @Valid @RequestBody UpdateBodyDTO updateBodyDTO){

        contactMessageService.updateMessage(id, updateBodyDTO);

        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/searchSubject")   //http://localhost:8000/contact/searchSubject?subject=abc
    public ResponseEntity<List<ContactMessageDTO>> searchBySubject(@RequestParam("subject") String subject){

        List<ContactMessageDTO> contactMessageDTOList = contactMessageService.searchBySubject(subject);

        return new ResponseEntity<>(contactMessageDTOList, HttpStatus.OK);
    }


    @GetMapping("/findByDate")    //http://localhost:8000/contact/findByDate?startDate=01.01.2022&endDate=01.10.2023
    public ResponseEntity<List<ContactMessageDTO>> getMessagesBetweenDates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){

        List<ContactMessageDTO> contactMessageDTOList = contactMessageService.getMessagesBetweenDates(startDate,endDate);

        return ResponseEntity.ok(contactMessageDTOList);


    }

    @GetMapping("/findByTime")    //http://localhost:8000/contact/findByTime?startTime=10:30&endTime=22:00
    public ResponseEntity<List<ContactMessageDTO>> getMessagesBetweenTimes(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){

        List<ContactMessageDTO> contactMessageDTOList = contactMessageService.getMessagesBetweenTimes(startTime,endTime);

        return ResponseEntity.ok(contactMessageDTOList);


    }


}
