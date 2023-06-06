package com.example.quizzApp.entity;

import com.example.quizzApp.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "QUIZ_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "pass_word", nullable = false)
    private String passWord;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles roles;

}
