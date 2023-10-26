package com.techproeducation.backendproject.initialwork.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String subject;

    @NotNull
    private String message;

    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDateTime;


    @PrePersist
    private void setDateTime(){
        ZoneId zoneid = ZoneId.of("America/New_York");
        LocalDateTime creationDate = LocalDateTime.now(zoneid);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String creationDateTimeString = creationDate.format(dateTimeFormatter);
        creationDateTime = LocalDateTime.parse(creationDateTimeString,dateTimeFormatter);

    }










}
