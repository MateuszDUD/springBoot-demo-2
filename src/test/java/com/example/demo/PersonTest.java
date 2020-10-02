package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.demo.api.PersonController;
import com.example.demo.model.Employee;
import com.example.demo.model.PersonJpa;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PersonTest {

    @Autowired
    private PersonController personController;

    @Test
    public void controllerLoaded() {
        assertThat(personController).isNotNull();
    }

    @Test
    public void whenSaveEmployeeWithoutNumber_getPersonWithoutNumber() {
        Employee employee = new Employee();
        employee.setFirstName("Jano");
        employee.setLastName("Van");
        personController.addEmployee(employee);

        ResponseEntity<?> responseEntity = personController.getPeopleByLastName("Van");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PersonJpa> list = (ArrayList<PersonJpa>) responseEntity.getBody();
        PersonJpa person = list.stream()
                .findFirst()
                .get();

        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(person.getLastName()).isEqualTo(employee.getLastName());
        assertThat(person.getNumber()).isNull();
        assertThat(person.getPriority()).isNull();

        personController.deletePersonById(person.getId());
    }

    @Test
    public void whenSaveEmployeewithNumber_getPersonWithNumber() {
        Employee employee = new Employee();
        employee.setFirstName("Jano");
        employee.setLastName("Van");
        employee.setNumberLong(456L);

        personController.addEmployee(employee);

        ResponseEntity<?> responseEntity = personController.getPeopleByLastName("Van");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PersonJpa> list = (ArrayList<PersonJpa>) responseEntity.getBody();
        PersonJpa person = list.stream()
                .findFirst()
                .get();

        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(person.getLastName()).isEqualTo(employee.getLastName());
        assertThat(person.getNumber()).isEqualTo(employee.getNumberLong());
        assertThat(person.getPriority()).isNull();

        personController.deletePersonById(person.getId());
    }
}
