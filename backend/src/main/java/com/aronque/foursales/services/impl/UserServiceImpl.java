package com.aronque.foursales.services.impl;

import com.aronque.foursales.entities.User;
import com.aronque.foursales.entities.dto.UserDTO;
import com.aronque.foursales.exceptions.InvalidAttributeException;
import com.aronque.foursales.exceptions.ResourceNotFoundException;
import com.aronque.foursales.repositories.UsersRepository;
import com.aronque.foursales.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("User")
public class UserServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public List<UserDTO> findAll() {
        return UserDTO.fromUser(repository.findAll());
    }

    @Override
    public UserDTO createNewEntity(User obj) {
        try {
            return UserDTO.fromUser(repository.save(obj));
        } catch (Exception e) {
            throw new InvalidAttributeException("Dados inválidos na criação do usuário.");
        }

    }

    @Override
    public boolean isLoginCorrect(String username, String requestPwd, BCryptPasswordEncoder encoder) {
        User user = repository.findByUsername(username);

        return user != null && encoder.matches(requestPwd, user.getPassword());
    }

    @Override
    public User findUserById(String userId) {
        return repository.findById(userId).get();
    }

    @Override
    public User findReferenceById(String userId) {
        return repository.getReferenceById(userId);
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        return UserDTO.fromUser(repository.findByUsername(username));
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsAdmin() {
        return repository.existsByRole(User.Role.ADMIN);
    }
}
