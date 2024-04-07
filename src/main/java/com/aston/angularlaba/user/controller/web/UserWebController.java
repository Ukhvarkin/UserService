package com.aston.angularlaba.user.controller.web;

import com.aston.angularlaba.user.dto.ClientDTO;
import com.aston.angularlaba.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UserWebController {
    private final UserService userService;

    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile() {
        return "userProfile";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("clientRegistrationDTO", new ClientDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid ClientDTO clientRegistrationDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.createUser(clientRegistrationDTO);
        return "redirect:login?success";
    }
}
