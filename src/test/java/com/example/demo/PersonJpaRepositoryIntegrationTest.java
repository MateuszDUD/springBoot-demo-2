package com.example.demo;

import com.example.demo.model.PersonJpa;
import com.example.demo.repository.PersonRepository;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonJpaRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenFindByLastName_thenReturnPersons() {
        PersonJpa person = new PersonJpa("aaa", "bbb");
        testEntityManager.persist(person);
        testEntityManager.flush();

        PersonJpa found = personRepository.findByFirstName(person.getFirstName());

        assertThat(found.getFirstName())
                .isEqualTo(person.getFirstName());
    }

    @Test
    public void findPeopleByLastName() {
        testEntityManager.persist(new PersonJpa("a", "b"));
        testEntityManager.persist(new PersonJpa("b", "a"));
        testEntityManager.persist(new PersonJpa("a", "a"));
        List<PersonJpa> list = personRepository.findByLastName("a");

        assertThat(list.size()).isEqualTo(2);
        assertThat(list).extracting(PersonJpa::getLastName).containsOnly("a");
    }

    @Test
    public void deletePersonById() {
        testEntityManager.persist(new PersonJpa("Jan", "a"));
        testEntityManager.persist(new PersonJpa("Marek", "a"));
        testEntityManager.persist(new PersonJpa("Lukas", "a"));

        PersonJpa found = personRepository.findByFirstName("Marek");
        personRepository.deleteById(found.getId());

        List<PersonJpa> list = personRepository.findByLastName("a");

        assertThat(list.size()).isEqualTo(2);
        assertThat(list).extracting(PersonJpa::getFirstName).doesNotContain("Marek");
    }

    @Test
    public void findPeopleByNames() {
        testEntityManager.persist(new PersonJpa("Jan", "a"));
        testEntityManager.persist(new PersonJpa("Marek", "a"));
        testEntityManager.persist(new PersonJpa("Lukas", "a"));

        List<PersonJpa> list = personRepository.findPeopleByNames("Jan", "a");

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void pagination() {
        for (int i = 0; i < 10; i++) {
            testEntityManager.persist(new PersonJpa("a", "b"));
        }

        Pageable firstFiveElements = PageRequest.of(0,5);
        Page<PersonJpa> list = personRepository.findAll(firstFiveElements);

        assertThat(list.getSize()).isEqualTo(5);
    }

    @Test
    public void sorting() {
        testEntityManager.persist(new PersonJpa("Jan", "a"));
        testEntityManager.persist(new PersonJpa("Marek", "a"));
        testEntityManager.persist(new PersonJpa("Anna", "a"));

        List<PersonJpa> list = personRepository.findAll(Sort.by("firstName"));

        assertThat(list.get(0).getFirstName()).isEqualTo("Anna");
    }

    @Test
    public void firstNameContain() {
        testEntityManager.persist(new PersonJpa("Jan", "a"));
        testEntityManager.persist(new PersonJpa("Marek", "a"));
        testEntityManager.persist(new PersonJpa("Anna", "a"));

        List<PersonJpa> list = personRepository.findPeopleByFirstNameContains("re");

        assertThat(list.size()).isEqualTo(1);
    }
}
