package com.cava.telephonedirectory.service;

import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.Person;
import com.cava.telephonedirectory.exception.PersonNotFoundException;
import com.cava.telephonedirectory.repository.PersonRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDto createPerson(PersonDto personDto) {
        Person person = dozerBeanMapper.map(personDto, Person.class);
        Person personSaved = personRepository.save(person);
        return dozerBeanMapper.map(personSaved, PersonDto.class);
    }

    public List<PersonDto> getAllPersons() {
        List<Person> personList = personRepository.findAll();

        return personList.stream().map(person ->
                dozerBeanMapper.map(person, PersonDto.class)
        ).collect(Collectors.toList());

    }

    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("not found person with id: " + id));
        return dozerBeanMapper.map(person, PersonDto.class);
    }

    public void deletePerson(Long id) {

        personRepository.deleteById(id);
    }
}
