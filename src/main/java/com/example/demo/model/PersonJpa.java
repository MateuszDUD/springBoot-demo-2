package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersonJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    protected PersonJpa() {};

    public PersonJpa(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
