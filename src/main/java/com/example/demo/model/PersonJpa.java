package com.example.demo.model;

import com.example.demo.enums.Priority;
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

    private Priority priority;

    private Long number;

    public PersonJpa() {};

    public PersonJpa(String firstName, String lastName, BigDecimal number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number.longValue();
    }

    public PersonJpa(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
