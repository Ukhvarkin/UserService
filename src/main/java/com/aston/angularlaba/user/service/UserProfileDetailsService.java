package com.aston.angularlaba.user.service;

import com.aston.angularlaba.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDetailsService implements UserDetailsService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " не найден"));
    }
}
