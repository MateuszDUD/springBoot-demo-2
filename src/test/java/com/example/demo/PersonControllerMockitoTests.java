package com.example.demo;

import com.example.demo.api.PersonController;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class PersonControllerMockitoTests {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void testDeletePersonSuccess() {
        given(personService.deletePersonById(5))
                .willReturn(true);

        ResponseEntity response = personController
                .deletePersonById(5);

        then(personService)
                .should()
                .deletePersonById(5);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeletePersonFailed() {
        given(personService.deletePersonById(5))
                .willReturn(false);

        ResponseEntity response = personController
                .deletePersonById(5);

        then(personService)
                .should()
                .deletePersonById(5);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
