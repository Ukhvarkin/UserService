package com.aston.angularlaba.user.controller;

import com.aston.angularlaba.user.dto.ClientDTO;
import com.aston.angularlaba.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/new-user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody ClientDTO clientDTO) {
        log.info("Запрос создание нового пользователя.");
        userService.createUser(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId,
                                             @Valid @RequestBody ClientDTO clientDTO){
        log.info("Запрос на обновление пользователя с id: {}.", userId);
        userService.updateUser(userId, clientDTO);
        return ResponseEntity.ok("Данные пользователя обновлены");
    }
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId){
        log.info("Запрос на удаление пользователя с id: {}.", userId);
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Пользователь удален");
    }
}
