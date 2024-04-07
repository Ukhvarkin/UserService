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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Transactional
    @Override
    public void updateUser(UUID userId, ClientDTO clientDTO) {
        userValidation(clientDTO);
        UserProfile existingUser = userProfileRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Не существует пользователя с id: " + userId));
        Client existingClient = clientRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Не существует клиента с id: " + userId));
        boolean updated = false;

        if (clientDTO.getFirstName() != null) {
            existingClient.setFirstName(clientDTO.getFirstName());
            updated = true;
            log.info("Редактирование имени: {}", clientDTO.getFirstName());
        }
        if (clientDTO.getLastName() != null) {
            existingClient.setLastName(clientDTO.getLastName());
            updated = true;
            log.info("Редактирование фамилии: {}", clientDTO.getLastName());
        }
        if (clientDTO.getMobilePhone() != null) {
            existingClient.setMobilePhone(clientDTO.getMobilePhone());
            updated = true;
            log.info("Редактирование номера телефона: {}", clientDTO.getMobilePhone());
        }
        if (clientDTO.getPassportNumber() != null) {
            existingClient.setPassportNumber(clientDTO.getPassportNumber());
            updated = true;
            log.info("Редактирование номера паспорта: {}", clientDTO.getPassportNumber());
        }
        if (clientDTO.getEmail() != null) {
            existingUser.setEmail(clientDTO.getEmail());
            updated = true;
            log.info("Редактирование почты: {}", clientDTO.getEmail());
        }
        if (clientDTO.getPassword() != null) {
            existingUser.setPassword(clientDTO.getPassword());
            updated = true;
            log.info("Редактирование пароля: {}", clientDTO.getPassword());
        }
        if (updated) {
            clientRepository.save(existingClient);
            userProfileRepository.save(existingUser);
            log.info("Данные пользователя с id {} успешно обновлены", userId);
        } else {
            log.info("Данные пользователя с id {} не изменились", userId);
        }
    }

    @Override
    public void deleteUserById(UUID userId) {
        userProfileRepository.deleteById(userId);
    }

    private void userValidation(ClientDTO clientDTO) {
        if (userProfileRepository.existsByEmail(clientDTO.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }

        if (clientRepository.existsByMobilePhone(clientDTO.getMobilePhone())) {
            throw new IllegalArgumentException("Пользователь с таким номером телефона уже существует.");
        }
    }
}
