package com.example.demo;

import com.example.demo.model.PersonJpa;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PersonServiceMockTest {


    @TestConfiguration
    static class PersonServiceMockTestConfiguration {

        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        PersonJpa person = new PersonJpa("Alex", "Ve");

        Mockito.when(personRepository.findByFirstName(person.getFirstName()))
                .thenReturn(person);

        Mockito.when(personRepository.existsById(5L))
                .thenReturn(false);
    }

    @Test
    public void whenValidName_thenPersonShouldBeFound() {
        String name = "Alex";
        PersonJpa found = personService.getPersonByFirstName(name);

        assertThat(found).isNotNull();
    }

    @Test
    public void whenDeletedInvalidId_thenFalseShouldBeReturned() {
        boolean v = personService.deletePersonById(5);

        assertThat(v).isEqualTo(false);
    }
}
