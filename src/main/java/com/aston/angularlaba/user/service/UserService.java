package com.aston.angularlaba.user.service;

import com.aston.angularlaba.user.dto.ClientDTO;
import com.aston.angularlaba.user.model.Client;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(ClientDTO clientRegistrationDTO);
    List<Client> getAll();
    void deleteUserById(UUID userId);
}
