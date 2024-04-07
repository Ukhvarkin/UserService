package com.aston.angularlaba.user.service;

import com.aston.angularlaba.user.dto.ClientRegistrationDTO;
import com.aston.angularlaba.user.dto.ClientUpdateDTO;
import com.aston.angularlaba.user.mapper.UserMapper;
import com.aston.angularlaba.user.model.Client;
import com.aston.angularlaba.user.model.Role;
import com.aston.angularlaba.user.model.UserProfile;
import com.aston.angularlaba.user.repository.ClientRepository;
import com.aston.angularlaba.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public void createUser(ClientRegistrationDTO clientRegistrationDTO) {
        Client client = userMapper.toClient(clientRegistrationDTO);
        client.setAccessionDate(LocalDate.now());
        clientRepository.save(client);

        clientRegistrationDTO.setPassword(passwordEncoder.encode(clientRegistrationDTO.getPassword()));
        UserProfile userProfile = userMapper.toUserProfile(clientRegistrationDTO);
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
    public void updateUser(ClientUpdateDTO clientUpdateDTO) {

    }

    @Override
    public void deleteUserByEmail(String email) {
        userProfileRepository.deleteByEmail(email);
    }


}
