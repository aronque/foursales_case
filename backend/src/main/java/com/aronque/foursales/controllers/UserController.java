package com.aronque.foursales.controllers;

import com.aronque.foursales.entities.User;
import com.aronque.foursales.entities.dto.CreateUserDTO;
import com.aronque.foursales.entities.dto.DefaultMessageDTO;
import com.aronque.foursales.entities.dto.UserDTO;
import com.aronque.foursales.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UserController {

    @Autowired
    @Qualifier("User")
    private UsersService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Operation(summary = "Lista todos os usuários",
            description = "Autenticado com usuário administrador, lista todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados"),
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }


    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já existe"),
    })
    @PostMapping("/create")
    public ResponseEntity createNewUser(@RequestBody CreateUserDTO userDto) {

        if(service.existsByUsername(userDto.getUsername())) {
            throw new BadCredentialsException("Usuário já existe.");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(User.Role.COMMON);

        service.createNewEntity(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DefaultMessageDTO("Usuário criado com sucesso"));
    }
}
