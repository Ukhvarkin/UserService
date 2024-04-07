package com.aston.angularlaba.user.controller.api;

import com.aston.angularlaba.user.model.Client;
import com.aston.angularlaba.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAll(Authentication authentication) {
        log.info("Запрос на получения списка клиентов.");
        if (authentication != null) {
            List<Client> clients = userService.getAll();
            log.info("Размер списка: {}.", clients.size());
            return ResponseEntity.ok(clients);
        } else {
            log.warn("Неавторизованный пользователь!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неавторизованный пользователь");
        }
    }
}
