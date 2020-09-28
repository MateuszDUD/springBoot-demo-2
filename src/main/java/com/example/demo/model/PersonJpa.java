package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class PersonJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    private BigDecimal number;

    public PersonJpa() {};

    public PersonJpa(String firstName, String lastName, BigDecimal number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public PersonJpa(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
