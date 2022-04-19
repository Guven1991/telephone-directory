package com.cava.telephonedirectory.repository;

import com.cava.telephonedirectory.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
