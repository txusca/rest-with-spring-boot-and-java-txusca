package com.txusca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.txusca.model.Person;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("Finding one person" + id);

        Person person =  new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Rafael");
        person.setLastName("Vicente");
        person.setAddres("Carangola - Minas Gerais - Brasil");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all persons");
        List<Person> persons =  new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Create a new person");
        person.setId(counter.incrementAndGet());
        return person;
    }

    public Person update(Person person) {
        logger.info("Editing a person");
        return person;
    }

    public void delete(String id) {
        logger.info(() -> "Deleting a person: " + id);
    }

    private Person mockPerson(int i) {
        i++;
        Person person =  new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name: " + i);
        person.setLastName("Last name: " + i);
        person.setAddres("Addres: " + i);
        person.setGender("Gender: " + i);

        return person;
    }

}
