package com.txusca.services;

import com.txusca.controllers.PersonController;
import com.txusca.converter.DozerConverter;
import com.txusca.data.vo.v1.PersonVO;
import com.txusca.exceptions.RequiredObjectIsNullException;
import com.txusca.exceptions.ResourceNotFoundException;
import com.txusca.model.Person;
import com.txusca.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Finding one person" + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this id"));

        var vo = DozerConverter.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all persons");

        var persons = DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);

        persons.stream()
                .forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel()));

        return persons;
    }

    public PersonVO create(PersonVO person) {

        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Create a new person");

        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Editing a person");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info(() -> "Deleting a person: " + id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this id"));

        repository.delete(entity);
    }
}
