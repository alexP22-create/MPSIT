package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "favorite_watch")
    private String favoriteWatch;

    @Column(name = "my_budget")
    private float myBudget;

    @ManyToOne
    //nume coloana din userTable foreign key la role table
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, String aboutMe, String favoriteWatch, float myBudget, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.aboutMe = aboutMe;
        this.favoriteWatch = favoriteWatch;
        this.myBudget = myBudget;
        this.role = role;
    }

}
