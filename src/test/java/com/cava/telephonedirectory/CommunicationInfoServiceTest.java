package com.cava.telephonedirectory;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.CommunicationInfo;
import com.cava.telephonedirectory.entity.Person;
import com.cava.telephonedirectory.model.CommunicationInformationType;
import com.cava.telephonedirectory.repository.CommunicationInfoRepository;
import com.cava.telephonedirectory.service.CommunicationInfoService;
import com.cava.telephonedirectory.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationInfoServiceTest {

    @Mock
    private CommunicationInfoRepository communicationInfoRepository;

    @Mock
    private PersonService personService;

    @InjectMocks
    private CommunicationInfoService communicationInfoService;

    Person person;
    PersonDto personDto;
    CommunicationInfo communicationInfo;

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

        communicationInfo = CommunicationInfo.builder()
                .id(1L)
                .communicationInformationType(CommunicationInformationType.LOCATION)
                .informationContent("Gümüşhane")
                .person(person)
                .build();

    }

    @Test
    public void createCommunicationInfo() {

        CommunicationInfoDto requestCommunicationInfoDto = CommunicationInfoDto.builder()
                .id(communicationInfo.getId())
                .communicationInformationType(communicationInfo.getCommunicationInformationType())
                .informationContent(communicationInfo.getInformationContent())
                .build();

        when(personService.getPersonById(any())).thenReturn(personDto);
        when(communicationInfoRepository.save(any())).thenReturn(communicationInfo);

        CommunicationInfoDto returnedCommunicationInfoDto = communicationInfoService
                .createCommunicationInfo(requestCommunicationInfoDto, 1L);

        assertEquals("Gümüşhane", returnedCommunicationInfoDto.getInformationContent());
        assertEquals(Optional.of(1L), Optional.ofNullable(returnedCommunicationInfoDto.getPerson().getId()));
        assertEquals("guven", returnedCommunicationInfoDto.getPerson().getUsername());


    }


}
