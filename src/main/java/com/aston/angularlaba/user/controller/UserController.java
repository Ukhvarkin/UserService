package com.aston.angularlaba.user.controller;

import com.aston.angularlaba.user.dto.ClientRegistrationDTO;
import com.aston.angularlaba.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/new-user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody ClientRegistrationDTO clientRegistrationDTO) {
        log.info("Запрос создание нового пользователя.");
        userService.createUser(clientRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }
}
