package com.example.demo.mapper;

import com.example.demo.model.Employee;
import com.example.demo.model.PersonJpa;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Mapper
public abstract class EmployeePersonMapper {

    public PersonJpa employeeToPersonJpa(Employee employee) {
        PersonJpa personJpa = new PersonJpa();
        personJpa.setNumber(new BigDecimal(employee.getNumberLong()));
        return personJpa;
    }

    public abstract List<PersonJpa> toPersonJpa(Collection<Employee> employees);
}
