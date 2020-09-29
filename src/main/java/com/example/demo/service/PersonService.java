package com.example.demo.service;

import com.example.demo.model.PersonJpa;
import com.example.demo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository repository;

    public PersonService() {}
    /*
    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
    */

    public void addPerson(PersonJpa personJpa) {
        repository.save(personJpa);
    }

    public List<PersonJpa> getPeopleByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public Iterable<PersonJpa> getAllPeople() {
        return repository.findAll();
    }

    public void deletePersonById(long id) {
        /*
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("One person deleted. ID: " + id);
            return true;
        } else {
            return false;
        }
         */
        repository.deleteById(id);
    }

    public Optional<PersonJpa> getPersonById(long id) {
        if (repository.existsById(Long.valueOf(id))) {
            return repository.findById(Long.valueOf(id));
        } else {
            return Optional.empty();
        }
    }

    public PersonJpa getPersonByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }
}
