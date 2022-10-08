package com.txusca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.txusca.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
