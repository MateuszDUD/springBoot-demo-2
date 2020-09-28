package com.example.demo.mapper;

import com.example.demo.model.Employee;
import com.example.demo.model.PersonJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonEmployeeMapper {

    //@Mapping(source="firstName", target="firstName")
    PersonJpa employeeToPerson(Employee employee);
}
