package com.financial.transfer.controllers;
import com.financial.transfer.dtos.AuthRequest;
import com.financial.transfer.dtos.AuthResponse;
import com.financial.transfer.models.UserModel;
import com.financial.transfer.repositories.UserRepository;
import com.financial.transfer.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200/register")
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.ok(new AuthResponse("Usuário já existe!"));
        }

        // Criptografa a senha
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest request) {
        try {
            var authInputToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }

//        var optUser = userRepository.findByEmail(user);
//        if (optUser.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
//        }
//
//        String token = jwtUtil.generateToken(user);
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
    }
}
