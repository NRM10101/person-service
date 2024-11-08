package com.devx.person_service.service;

import com.devx.person_service.exception.PersonNotFoundException;
import com.devx.person_service.model.Person;
import com.devx.person_service.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        Optional<Person> existingPersonOpt = personRepository.findById(id);
        if(existingPersonOpt.isPresent())
        {
            return existingPersonOpt.get();
        }
        else{
            throw new PersonNotFoundException("Person not found");
        }
    }

    public void deleteById(Long id) {
        Optional<Person> existingPersonOpt = personRepository.findById(id);
        if(existingPersonOpt.isPresent())
        {
            personRepository.deleteById(id);
        }
        else{
            throw new PersonNotFoundException("Person not found");
        }
    }
}
