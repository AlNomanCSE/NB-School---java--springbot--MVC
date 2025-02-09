package com.noman.nbSchool.repository;

import com.noman.nbSchool.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
