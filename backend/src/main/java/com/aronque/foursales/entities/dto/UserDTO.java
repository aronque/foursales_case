package com.aronque.foursales.entities.dto;

import com.aronque.foursales.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String username;
    private String role;

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getUsername(), String.valueOf(user.getRole()));
    }

    public static List<UserDTO> fromUser(List<User> users) {
        return users.stream().map(UserDTO::fromUser).toList();
    }
}
