package com.example.demo;

import com.example.demo.api.PersonController;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.model.PersonJpa;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PersonService personService;

    @Test()
    public void validateRequestMatching() throws Exception {
        mockMvc.perform(get("/api/jpa/person")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenNotExistingId_thenReturn404() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/jpa/person/id/{id}", 5)
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();

        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = "Could not find person: 5";

        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }
}
