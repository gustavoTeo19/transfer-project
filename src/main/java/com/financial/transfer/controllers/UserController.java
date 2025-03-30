package com.financial.transfer.controllers;
import com.financial.transfer.dtos.AuthResponse;
import com.financial.transfer.models.UserModel;
import com.financial.transfer.repositories.UserRepository;
import com.financial.transfer.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody UserModel user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Usuário já existe!";
        }

        // Criptografa a senha
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());

        return "Usuário registrado com sucesso!";
    }

    @GetMapping("/token")
    public ResponseEntity<?> getToken(@RequestParam("user") String user){
        var optUser = userRepository.findByUsername(user);
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
