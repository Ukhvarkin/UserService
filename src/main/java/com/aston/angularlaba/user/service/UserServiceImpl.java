package com.aston.angularlaba.user.service;

import com.aston.angularlaba.user.dto.ClientDTO;
import com.aston.angularlaba.user.mapper.UserMapper;
import com.aston.angularlaba.user.model.Client;
import com.aston.angularlaba.user.model.Role;
import com.aston.angularlaba.user.model.UserProfile;
import com.aston.angularlaba.user.repository.ClientRepository;
import com.aston.angularlaba.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ClientRepository clientRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public void createUser(ClientDTO clientDTO) {
        userValidation(clientDTO);

        Client client = userMapper.toClient(clientDTO);
        client.setAccessionDate(LocalDate.now());
        clientRepository.save(client);

        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        UserProfile userProfile = userMapper.toUserProfile(clientDTO);
        userProfile.setRole(Role.CLIENT);
        userProfile.setClientId(client);

        userProfileRepository.save(userProfile);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteUserById(UUID userId) {
        userProfileRepository.deleteById(userId);
    }

    private void userValidation(ClientDTO clientDTO) {
        if (userProfileRepository.existsByEmail(clientDTO.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        if (clientRepository.existsByMobilePhone(clientDTO.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким номером телефона уже существует");
        }
    }
}
