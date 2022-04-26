package com.cava.telephonedirectory.service;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.dto.PersonDto;
import com.cava.telephonedirectory.entity.CommunicationInfo;
import com.cava.telephonedirectory.model.CommunicationInformationType;
import com.cava.telephonedirectory.model.LocationInfoModel;
import com.cava.telephonedirectory.repository.CommunicationInfoRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CommunicationInfoDto> getCommunicationInfosByLocation(String location) {

        List<CommunicationInfo> communicationInfoList = communicationInfoRepository
                .getCommunicationInfosByCommunicationInformationTypeAndInformationContent(CommunicationInformationType.LOCATION, location);
        return communicationInfoList.stream().map(communicationInfo ->
                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());

    }

    public List<CommunicationInfoDto> getAllCommunicationInfoByPersonId(Long id) {
        List<CommunicationInfo> communicationInfoList = communicationInfoRepository.getCommunicationInfosByPerson_Id(id);

        return communicationInfoList.stream().map(communicationInfo ->
                dozerBeanMapper.map(communicationInfo, CommunicationInfoDto.class)).collect(Collectors.toList());
    }

    public void deleteCommunicationInfo(Long id) {
        communicationInfoRepository.deleteById(id);
    }

    public List<LocationInfoModel> getPersonCountByLocation() {
        return communicationInfoRepository.getPersonCountByLocation();
    }

    public List<LocationInfoModel> getLocationInfo() {

        Long phoneCount = 0L;

        List<LocationInfoModel> locationInfoModelList = new ArrayList<>();
        List<LocationInfoModel> personCountLocationModelList = getPersonCountByLocation();

        for (LocationInfoModel locationInfoModel : personCountLocationModelList) {

            String location = locationInfoModel.getLocation();
            List<CommunicationInfoDto> communicationInfoDtoList = getCommunicationInfosByLocation(location);

            for (CommunicationInfoDto communicationInfoDto : communicationInfoDtoList) {

                List<CommunicationInfoDto> communicationInfoDtoListForPerson = personService
                        .getPersonById(communicationInfoDto.getPerson().getId()).getCommunicationInfoList();

                for (CommunicationInfoDto info : communicationInfoDtoListForPerson) {

                    if (info.getCommunicationInformationType().equals(CommunicationInformationType.PHONE_NUMBER)) {
                        phoneCount++;
                    }
                }
            }

            locationInfoModel.setPhoneNumberCount(phoneCount);
            locationInfoModelList.add(locationInfoModel);


            phoneCount = 0L;
        }

        return locationInfoModelList;
    }

}
