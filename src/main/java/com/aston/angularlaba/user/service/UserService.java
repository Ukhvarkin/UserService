package com.aston.angularlaba.user.service;

import com.aston.angularlaba.user.dto.ClientRegistrationDTO;
import com.aston.angularlaba.user.dto.ClientUpdateDTO;
import com.aston.angularlaba.user.model.Client;

import java.util.List;

public interface UserService {
    void createUser(ClientRegistrationDTO clientRegistrationDTO);
    List<Client> getAll();
    void updateUser(ClientUpdateDTO clientUpdateDTO);
    void deleteUserByEmail(String email);
}
