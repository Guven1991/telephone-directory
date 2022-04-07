package com.cava.telephonedirectory.controller;

import com.cava.telephonedirectory.dto.CommunicationInfoDto;
import com.cava.telephonedirectory.request.CommunicationInfoCreateRequest;
import com.cava.telephonedirectory.response.CommunicationInfoResponse;
import com.cava.telephonedirectory.service.CommunicationInfoService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public void deleteCommunicationInfo(@PathVariable Long id){
        communicationInfoService.deleteCommunicationInfo(id);
    }

}
