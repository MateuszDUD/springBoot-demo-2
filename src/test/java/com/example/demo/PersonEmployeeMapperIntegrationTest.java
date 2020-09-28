package com.example.demo;

import com.example.demo.mapper.EmployeePersonMapper;
import com.example.demo.mapper.PersonEmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.PersonJpa;
import org.apache.catalina.mapper.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PersonEmployeeMapperIntegrationTest {

    private PersonEmployeeMapper mapperInterface =
            Mappers.getMapper(PersonEmployeeMapper.class);

    private EmployeePersonMapper mapperAClass =
            Mappers.getMapper(EmployeePersonMapper.class);

    @Test
    public void employeeToPersonInterfaceTest() {
        Employee employee = new Employee();
        employee.setFirstName("A");
        employee.setLastName("B");

        PersonJpa person = mapperInterface.employeeToPerson(employee);

        assertThat(person).isNotNull();
        assertEquals(employee.getFirstName(), person.getFirstName());
        assertEquals(employee.getLastName(), person.getLastName());
    }

    @Test
    public void employeeToPersonAbstractClassTest() {
        Employee employee = new Employee();
        employee.setLastName("a");
        employee.setLastName("b");
        employee.setNumberLong(50L);

        PersonJpa person = mapperAClass.employeeToPersonJpa(employee);

        assertThat(person).isNotNull();
        assertEquals(employee.getFirstName(), person.getFirstName());
        assertEquals(employee.getNumberLong().longValue(), person.getNumber().longValue());
    }
}
