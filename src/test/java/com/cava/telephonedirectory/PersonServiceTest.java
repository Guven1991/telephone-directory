package com.cava.telephonedirectory;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.Person;
import com.cava.telephonedirectory.model.CommunicationInformationType;
import com.cava.telephonedirectory.repository.PersonRepository;
import com.cava.telephonedirectory.service.CommunicationInfoService;
import com.cava.telephonedirectory.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CommunicationInfoService communicationInfoService;

    @InjectMocks
    private PersonService personService;

    Person person;
    PersonDto personDto;


    @Before
    public void init() {

        person = Person.builder()
                .id(1L)
                .username("guven")
                .lastname("ayvazoglu")
                .company("cava.ltd")
                .build();

        personDto = PersonDto.builder()
                .id(person.getId())
                .username(person.getUsername())
                .lastname(person.getLastname())
                .company(person.getCompany())
                .build();


    }

    @Test
    public void createPerson() {
        when(personRepository.save(any())).thenReturn(person);
        PersonDto personReturnDto = personService.createPerson(personDto);
        assertEquals(Optional.of(1L), Optional.ofNullable(personReturnDto.getId()));
    }

    @Test
    public void deletePerson() {
//        when(personRepository.existsById(any())).thenReturn(true);
        personService.deletePerson(1L);
        verify(personRepository).deleteById(1L);
    }

    @Test
    public void getAllPersons() {
        when(personRepository.findAll()).thenReturn(List.of(person));
        List<PersonDto> personDtoList = personService.getAllPersons();
        assertEquals(1, personDtoList.size());
        assertEquals("guven", personDtoList.get(0).getUsername());
    }

    @Test
    public void getPersonById() {
        CommunicationInfoDto communicationInfoDto = CommunicationInfoDto.builder()
                .id(10L)
                .communicationInformationType(CommunicationInformationType.LOCATION)
                .informationContent("Gümüşhane")
                .build();

        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(person));
        when(communicationInfoService.getAllCommunicationInfoByPersonId(any())).thenReturn(List.of(communicationInfoDto));
        PersonDto personDto = personService.getPersonById(1L);
        assertEquals(Optional.of(1L), Optional.ofNullable(personDto.getId()));
        assertEquals("ayvazoglu", personDto.getLastname());
        assertEquals("Gümüşhane",personDto.getCommunicationInfoList().get(0).getInformationContent());
    }


}
