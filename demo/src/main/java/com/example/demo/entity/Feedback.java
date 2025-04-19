package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "feedback", schema = "public")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "comment", unique = true)
    private String comment;

    public Feedback() {
    }

    public Feedback(String comment) {
        this.comment = comment;
    }

}
