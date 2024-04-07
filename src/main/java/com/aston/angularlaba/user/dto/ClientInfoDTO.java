package com.aston.angularlaba.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientInfoDTO {
    private String firstName;
    private String lastName;
    private String email;
}
