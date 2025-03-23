package com.financial.transfer.controllers;
import com.financial.transfer.models.UserModel;
import com.financial.transfer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserModel user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Usuário já existe!";
        }

        // Criptografa a senha
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "Usuário registrado com sucesso!";
    }
}
