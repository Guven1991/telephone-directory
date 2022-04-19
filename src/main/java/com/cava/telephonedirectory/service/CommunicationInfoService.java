package com.cava.telephonedirectory.service;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.CommunicationInfo;
import com.cava.telephonedirectory.model.CommunicationInformationType;
import com.cava.telephonedirectory.model.PersonCountLocationModel;
import com.cava.telephonedirectory.repository.CommunicationInfoRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommunicationInfoService {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final PersonService personService;

    private final CommunicationInfoRepository communicationInfoRepository;

    public CommunicationInfoService(@Lazy PersonService personService, CommunicationInfoRepository communicationInfoRepository) {
        this.personService = personService;
        this.communicationInfoRepository = communicationInfoRepository;
    }


    public CommunicationInfoDto createCommunicationInfo(CommunicationInfoDto communicationInfoDto, Long personId) {
        PersonDto personDto = personService.getPersonById(personId);

        communicationInfoDto.setPerson(personDto);
        communicationInfoDto.setInformationContent(communicationInfoDto.getInformationContent().toUpperCase().trim());
        CommunicationInfo communicationInfo = dozerBeanMapper.map(communicationInfoDto, CommunicationInfo.class);
        CommunicationInfo communicationInfoSaved = communicationInfoRepository.save(communicationInfo);
        return dozerBeanMapper.map(communicationInfoSaved, CommunicationInfoDto.class);

    }

    public List<CommunicationInfoDto> getAllCommunicationInfo() {
        List<CommunicationInfo> communicationInfoList = communicationInfoRepository.findAll();

        return communicationInfoList.stream().map(communicationInfo ->
                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());

    }

    public List<CommunicationInfoDto> getCommunicationInfosByLocationContent(String locationContent) {

        List<CommunicationInfo> communicationInfoList = communicationInfoRepository
                .getCommunicationInfosByCommunicationInformationTypeAndInformationContent(CommunicationInformationType.LOCATION, locationContent);
        return communicationInfoList.stream().map(communicationInfo ->
                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());

    }

//    public List<CommunicationInfoDto> getCommunicationInfosByPhoneNumberContent(String locationContent) {
//
//        List<CommunicationInfo> communicationInfoList = communicationInfoRepository
//                .getCommunicationInfosByCommunicationInformationTypeAndInformationContent(CommunicationInformationType.PHONE_NUMBER, locationContent);
//        return communicationInfoList.stream().map(communicationInfo ->
//                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());
//
//    }

    public List<CommunicationInfoDto> getAllCommunicationInfoByPersonId(Long id) {
        List<CommunicationInfo> communicationInfoList = communicationInfoRepository.getCommunicationInfosByPerson_Id(id);

        return communicationInfoList.stream().map(communicationInfo ->
                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());
    }

    public void deleteCommunicationInfo(Long id) {
        communicationInfoRepository.deleteById(id);
    }

    public List<PersonCountLocationModel> getLocationCount() {
        List<PersonCountLocationModel> personCountLocationModelList = communicationInfoRepository.getLocationCount();
        return personCountLocationModelList;
    }

    public void getPhoneNumberCount() {

        Integer phoneCount = 0;

        List<PersonCountLocationModel> personCountLocationModelList = getLocationCount();

        for (int i = 0; i < personCountLocationModelList.size(); i++) {

            String cityName = personCountLocationModelList
                    .get(i)
                    .getInformationContent();

            List<CommunicationInfoDto> communicationInfoDtoList = getCommunicationInfosByLocationContent(personCountLocationModelList
                    .get(i)
                    .getInformationContent());

            for (int j = 0; j < communicationInfoDtoList.size(); j++) {

                List<CommunicationInfoDto> communicationInfoDtoList1 = personService.getPersonById(communicationInfoDtoList.get(j).getPerson().getId()).getCommunicationInfoList();

                for (int k = 0; k < communicationInfoDtoList1.size(); k++) {

                    if (communicationInfoDtoList1.get(k).getCommunicationInformationType().equals(CommunicationInformationType.PHONE_NUMBER)) {
                        phoneCount++;
                    }
                }
            }
//            todo hashmap ile sakla
            System.out.println(cityName + " : " + phoneCount);
            phoneCount=0;
        }

//        personCountLocationModelList.stream().map(p -> {
//            List<CommunicationInfoDto> communicationInfoDtoList = getCommunicationInfosByLocationContent(p.getInformationContent());
//            List<CommunicationInfo> communicationInfoDtos = new ArrayList<>();
//            communicationInfoDtos = communicationInfoDtoList.stream().map(c -> {
//                personService.getPersonById(c.getPerson().getId()).getCommunicationInfoList().stream()
//                        .filter(ci -> ci.getCommunicationInformationType().equals(CommunicationInformationType.PHONE_NUMBER))
//                        .collect(Collectors.toList());
//
//                communicationInfoDtos.size();
//            });
//
//            List<CommunicationInfoDto> communicationInfoDtoList = getCommunicationInfosByLocationContent()
//            List<PhoneNumberCountByLocation> phoneNumberCountByLocationList = communicationInfoRepository.getPhoneNumberCount();
//            return phoneNumberCountByLocationList;
        }
    }
