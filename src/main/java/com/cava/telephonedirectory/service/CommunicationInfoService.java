package com.cava.telephonedirectory.service;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.CommunicationInfo;
import com.cava.telephonedirectory.repository.CommunicationInfoRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunicationInfoService {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private PersonService personService;

    private CommunicationInfoRepository communicationInfoRepository;

    public CommunicationInfoService(@Lazy PersonService personService, CommunicationInfoRepository communicationInfoRepository) {
        this.personService = personService;
        this.communicationInfoRepository = communicationInfoRepository;
    }


    public CommunicationInfoDto createCommunicationInfo(CommunicationInfoDto communicationInfoDto, Long personId) {
        PersonDto personDto = personService.getPersonById(personId);
        //iç içe hatası alırsak communication Info Listi nulla

        communicationInfoDto.setPerson(personDto);
        CommunicationInfo communicationInfo = dozerBeanMapper.map(communicationInfoDto, CommunicationInfo.class);
        CommunicationInfo communicationInfoSaved = communicationInfoRepository.save(communicationInfo);
        return dozerBeanMapper.map(communicationInfoSaved, CommunicationInfoDto.class);

    }

    public List<CommunicationInfoDto> getAllCommunicationInfoByPersonId(Long id) {
        List<CommunicationInfo> communicationInfoList = communicationInfoRepository.getCommunicationInfosByPerson_Id(id);

        return communicationInfoList.stream().map(communicationInfo ->
                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());
    }

    public void deleteCommunicationInfo(Long id) {
        communicationInfoRepository.deleteById(id);
    }
}
