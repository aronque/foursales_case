package com.aronque.foursales.services;

import com.aronque.foursales.entities.User;
import com.aronque.foursales.entities.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UsersService {

    UserDTO createNewEntity(User obj);

    List<UserDTO> findAll();

    boolean isLoginCorrect(String username, String password, BCryptPasswordEncoder encoder);

    User findUserById(String id);

    User findReferenceById(String id);

    UserDTO findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsAdmin();
}
