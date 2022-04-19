package com.cava.telephonedirectory.service;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.Person;
import com.cava.telephonedirectory.exception.PersonNotFoundException;
import com.cava.telephonedirectory.repository.PersonRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final CommunicationInfoService communicationInfoService;

    public PersonService(PersonRepository personRepository, @Lazy CommunicationInfoService communicationInfoService) {
        this.personRepository = personRepository;
        this.communicationInfoService = communicationInfoService;
    }

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public PersonDto createPerson(PersonDto personDto) {
        String personDtoUsername = personDto.getUsername().toUpperCase().trim();
        String personDtoLastName = personDto.getLastname().toUpperCase().trim();
        String personDtoCompany = personDto.getCompany().toUpperCase().trim();
        personDto.setUsername(personDtoUsername);
        personDto.setLastname(personDtoLastName);
        personDto.setCompany(personDtoCompany);

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
        List<CommunicationInfoDto> communicationInfoDtoList = communicationInfoService.getAllCommunicationInfoByPersonId(id);
        PersonDto personDto = dozerBeanMapper.map(person, PersonDto.class);
        personDto.setCommunicationInfoList(communicationInfoDtoList);

        return personDto;
    }

    public void deletePerson(Long id) {

        personRepository.deleteById(id);
    }
}
