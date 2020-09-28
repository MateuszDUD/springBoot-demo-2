package com.example.demo;

import com.example.demo.mapper.PersonEmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.PersonJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PersonEmployeeMapperIntegrationTest {

    private PersonEmployeeMapper mapper =
            Mappers.getMapper(PersonEmployeeMapper.class);

    @Test
    public void employeeToPersonTest() {
        Employee employee = new Employee();
        employee.setFirstName("A");
        employee.setLastName("B");

        PersonJpa person = mapper.employeeToPerson(employee);

        assertThat(person).isNotNull();
        assertEquals(employee.getFirstName(), person.getFirstName());
        assertEquals(employee.getLastName(), person.getLastName());
    }
}
