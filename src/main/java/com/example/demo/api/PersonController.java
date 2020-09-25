package com.example.demo.api;

import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.model.PersonJpa;
import com.example.demo.model.Quote;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("api/jpa/person")
@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody PersonJpa person) {
        personService.addPerson(person);
    }

    @GetMapping(path = "{lastName}")
    public List<PersonJpa> getPeopleByLastName(@PathVariable String lastName) {
        return personService.getPeopleByLastName(lastName);
    }

    @GetMapping
    public Iterable<PersonJpa> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public PersonJpa getPersonById(@PathVariable long id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deletePersonById(@PathVariable long id) {
        if (personService.deletePersonById(id)) {
            return ResponseEntity
                    .ok()
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}


