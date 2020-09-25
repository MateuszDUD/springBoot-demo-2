package com.example.demo.service;

import com.example.demo.model.PersonJpa;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

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

    public boolean deletePersonById(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
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
