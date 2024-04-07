package com.aston.angularlaba.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;
    @NotNull
    @Size(max = 30)
    private String firstName;

    @NotNull
    @Size(max = 30)
    private String lastName;

    @NotNull
    @Size(max = 11)
    //@Column(unique = true)
    private String mobilePhone;

    @NotNull
    @Size(max = 10)
    private String passportNumber;

    private LocalDate accessionDate;
}