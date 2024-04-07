package com.aston.angularlaba.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @NotBlank(message = "First name is required")
    @Size(max = 30, message = "First name cannot be longer than 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 30, message = "Last name cannot be longer than 30 characters")
    private String lastName;

    @Pattern(regexp = "\\d{11}", message = "Mobile phone number must be 11 digits")
    private String mobilePhone;

    @Pattern(regexp = "\\d{10}", message = "Passport number must be 10 digits")
    private String passportNumber;

    private LocalDate accessionDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(min = 10, max = 64, message = "Email cannot be shorter than 10 or longer than 64 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Email cannot be shorter than 10 or longer than 64 characters")
    private String password;
}
