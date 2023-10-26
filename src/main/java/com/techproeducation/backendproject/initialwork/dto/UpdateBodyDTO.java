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
public class UpdateBodyDTO {


    private String name;


    @Email
    private String email;


    private String subject;


    private String message;


}
