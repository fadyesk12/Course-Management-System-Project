package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
// @NoArgsConstructor
// @Entity
// @Table(name = "user")
public abstract class User {
    // @Id
    // @SequenceGenerator(
    //         name = "user_sequence",
    //         sequenceName = "user_sequence",
    //         initialValue = 77770001,
    //         allocationSize = 1
    // )
    // @GeneratedValue(
    //         strategy = GenerationType.SEQUENCE,
    //         generator = "user_sequence"
    // )
    // @Getter
    // private Long id;
    // @Getter
    // @Column(name = "role")
    private String role;
    public abstract String getRole();
}
