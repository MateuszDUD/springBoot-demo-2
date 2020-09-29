package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class PersonJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String firstName;


    @NotNull
    @NotBlank
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
