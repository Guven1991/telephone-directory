package com.cava.telephonedirectory.controller;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.model.PersonCountLocationModel;
import com.cava.telephonedirectory.request.CommunicationInfoCreateRequest;
import com.cava.telephonedirectory.response.CommunicationInfoResponse;
import com.cava.telephonedirectory.service.CommunicationInfoService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/communicationInfo")
public class CommunicationInfoController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private CommunicationInfoService communicationInfoService;

    public CommunicationInfoController(CommunicationInfoService communicationInfoService) {
        this.communicationInfoService = communicationInfoService;
    }

    @PostMapping
    public ResponseEntity<CommunicationInfoResponse> createCommunicationInfo(
            @RequestParam Long personId,
            @RequestBody CommunicationInfoCreateRequest communicationInfoCreateRequest) {

        CommunicationInfoDto communicationInfoDto = dozerBeanMapper.map(communicationInfoCreateRequest, CommunicationInfoDto.class);

        return ResponseEntity.ok(dozerBeanMapper.map(communicationInfoService.createCommunicationInfo(communicationInfoDto,personId), CommunicationInfoResponse.class));
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<CommunicationInfoResponse>> getCommunicationInfosByLocationContent(
            @PathVariable("location") String locationContent){
        List<CommunicationInfoDto> communicationInfoDtoList = communicationInfoService.getCommunicationInfosByLocationContent(locationContent);

       return ResponseEntity.ok(communicationInfoDtoList.stream()
               .map(communicationInfoDto ->
                       dozerBeanMapper.map(communicationInfoDto,CommunicationInfoResponse.class)).collect(Collectors.toList()));


    }

    @GetMapping("/personCountByLocation")
    public ResponseEntity<List<PersonCountLocationModel>> getLocationCount(){
        return ResponseEntity.ok(communicationInfoService.getLocationCount());
    }

    @GetMapping("/phoneNumberCountByLocation")
    public void getPhoneNumberCount(){
        communicationInfoService.getPhoneNumberCount();
    }


    @DeleteMapping("/{id}")
    public void deleteCommunicationInfo(@PathVariable Long id){
        communicationInfoService.deleteCommunicationInfo(id);
    }

}
