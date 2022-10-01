package com.txusca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txusca.exceptions.ResourceNotFoundException;
import com.txusca.model.Person;
import com.txusca.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id) {
        logger.info("Finding one person" + id);

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this id"));
    }

    public List<Person> findAll() {
        logger.info("Finding all persons");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Create a new person");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Editing a person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info(() -> "Deleting a person: " + id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this id"));

        repository.delete(entity);
    }
}
