package com.example.quizzApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "QUIZ_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column(name = "pass_word", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_fk", referencedColumnName = "id")
    private Role roles;

}
