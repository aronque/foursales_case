package com.aronque.foursales.config;

import com.aronque.foursales.entities.User;
import com.aronque.foursales.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        if(!usersService.existsAdmin()) {
            usersService.createNewEntity(new User("admin", passwordEncoder.encode("123"), User.Role.ADMIN));
        }
    }
}