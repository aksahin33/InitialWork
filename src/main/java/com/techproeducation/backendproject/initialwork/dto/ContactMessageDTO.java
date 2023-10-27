package com.techproeducation.backendproject.initialwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageDTO {

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Email cannot be null.")
    @Email
    private String email;

    @NotNull(message = "Subject cannot be null.")
    private String subject;

    @NotNull(message = "Message cannot be null.")
    private String message;

}
