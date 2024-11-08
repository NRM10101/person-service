package com.devx.person_service.controller;

import com.devx.person_service.exception.PersonNotFoundException;
import com.devx.person_service.model.Person;
import com.devx.person_service.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //add person
    //post
    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        try{
            Person savedPerson = personService.addPerson(person);
            return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
        }catch (Exception e){
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all person
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        try{
            List<Person> allPersons = personService.getAll();
            return new ResponseEntity<>(allPersons, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get a person by id
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        try{
            Person person = personService.getById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        catch (PersonNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete person by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable Long id){
        try{
            personService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
