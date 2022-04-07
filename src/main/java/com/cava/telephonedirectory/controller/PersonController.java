package com.cava.telephonedirectory.controller;

import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.request.PersonCreateRequest;
import com.cava.telephonedirectory.response.PersonResponse;
import com.cava.telephonedirectory.service.PersonService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonCreateRequest personCreateRequest) {
        PersonDto personDto = dozerBeanMapper.map(personCreateRequest, PersonDto.class);

        return ResponseEntity.ok(dozerBeanMapper.map(personService.createPerson(personDto), PersonResponse.class));
    }
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPersons(){
        List<PersonDto> personDtoList =personService.getAllPersons();

        return ResponseEntity.ok(personDtoList.stream().map(personDto ->
                dozerBeanMapper.map(personDto,PersonResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable("id") Long id){

       return ResponseEntity.ok(dozerBeanMapper.map(personService.getPersonById(id),PersonResponse.class));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PersonResponse> updatePerson(@RequestBody PersonDto personDto, @PathVariable Long id){
//
//        PersonDto personReturnedDto = personService.updatePerson(personDto,id);
//
//        return ResponseEntity.ok(dozerBeanMapper.map(personReturnedDto, PersonResponse.class));
//
//    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
