package com.aston.angularlaba.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientUpdateDTO {
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String passportNumber;
    private String email;
    private String password;
}
