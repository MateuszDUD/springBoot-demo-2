package com.example.demo.repository;

import com.example.demo.model.PersonJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonJpa, Long> {
    List<PersonJpa> findByLastName(String lastNamse);

    PersonJpa findByFirstName(String firstName);

    PersonJpa findById(long id);

    @Query(value="SELECT p FROM PersonJpa p WHERE p.firstName = ?1 AND p.lastName = ?2")
    List<PersonJpa> findPeopleByNames(String fName, String lName);

    //@Query("SELECT p FROM PersonJpa WHERE p.firstName LIKE CONCAT('%', :letters, '%')")
    List<PersonJpa> findPeopleByFirstNameContains(String letters);

    @Override
    Optional<PersonJpa> findById(Long aLong);
}
