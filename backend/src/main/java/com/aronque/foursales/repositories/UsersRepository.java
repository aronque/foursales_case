package com.aronque.foursales.repositories;

import com.aronque.foursales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByRole(User.Role role);
}
