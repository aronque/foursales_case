package com.aronque.foursales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "USERS")
@NoArgsConstructor
public class User {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotEmpty(message = "O usuário não pode ser nulo")
    @Column(nullable = false)
    private String username;

    @NotEmpty(message = "A senha não pode ser nula")
    @Column(nullable = false)
    private String password;

    @Transient
    @OneToMany(mappedBy = "orderUser", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Role role;

    public enum Role {
        ADMIN (0),
        COMMON (1);

        public final int intValue;

        Role() {
            this.intValue = 0;
        }

        Role(int value) {
            this.intValue = value;
        }
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
