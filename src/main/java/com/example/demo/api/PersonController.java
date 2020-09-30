package com.example.demo.api;

import com.example.demo.enums.Priority;
import com.example.demo.errors.PersonValidationErrorBuilder;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.model.PersonJpa;
import com.example.demo.model.Quote;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("api/person")
@RestController
public class PersonController {
    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Object> addPerson(@RequestBody @Valid PersonJpa person, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(PersonValidationErrorBuilder.fromBindingErrors(errors));
        }

        personService.addPerson(person);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping(path = "{lastName}")
    public ResponseEntity<List<PersonJpa>> getPeopleByLastName(@PathVariable String lastName) {
        List<PersonJpa> listOfPeople = personService.getPeopleByLastName(lastName);
        if (listOfPeople.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfPeople);
    }

    @GetMapping
    public ResponseEntity<Iterable<PersonJpa>> getAllPeople(@RequestParam(required = false, name = "size") Integer size,
                                                            @RequestParam(required = false, name = "page") Integer page) {
        Iterable<PersonJpa> people;
        if (size == null || page == null) {
            people = personService.getAllPeople();
            logger.debug("Size: " + size);
            logger.debug("Page" + page);
        } else {
            people = personService.getAllPeoplePaginated(PageRequest.of(page, size));
        }

        if (people != null) {
            return ResponseEntity.ok(people);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<PersonJpa> getPersonById(@PathVariable long id) {
        PersonJpa person = personService.getPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        logger.info("found person with id: " + person.getId());
        return ResponseEntity.ok(person);
    }

    @GetMapping(path = "/priority")
    public ResponseEntity<?> getPeopleByPriority(@RequestParam(name = "priority") Priority priority) {
        return ResponseEntity.ok(personService.getPeopleByPriority(priority));
    }

    @GetMapping(path = "/number")
    public ResponseEntity<?> getPeopleByNumber(@RequestParam(name = "min")BigDecimal min,
                                               @RequestParam(name = "max")BigDecimal max) {
        return ResponseEntity.ok(personService.getPeopleByNumberBetween(min, max));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deletePersonById(@PathVariable long id) {
        personService.deletePersonById(id);
        return ResponseEntity.ok().build();
    }
}