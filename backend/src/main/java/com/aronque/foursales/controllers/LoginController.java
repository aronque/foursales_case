package com.aronque.foursales.controllers;

import com.aronque.foursales.entities.dto.LoginRequestDTO;
import com.aronque.foursales.entities.dto.LoginResponseDTO;
import com.aronque.foursales.entities.dto.UserDTO;
import com.aronque.foursales.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@Tag(name = "Login", description = "Endpoints para gerenciamento de login")
public class LoginController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Operation(summary = "Realiza o Login do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem sucedido"),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest) {

        if(!usersService.isLoginCorrect(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                passwordEncoder)) {
            throw new BadCredentialsException("Usuário ou senha incorretos.");
        }

        Instant now = Instant.now();
        Long ttl = 1000L;

        UserDTO user = usersService.findUserByUsername(loginRequest.getUsername());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("app")
                .subject(user.getId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(ttl))
                .claim("scope", List.of(user.getRole()))
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, ttl));
    }
}