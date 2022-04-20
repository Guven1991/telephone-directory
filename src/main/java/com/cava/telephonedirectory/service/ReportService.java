package com.cava.telephonedirectory.service;

import com.cava.telephonedirectory.dto.ReportDto;
import com.cava.telephonedirectory.model.LocationInfoModel;
import com.cava.telephonedirectory.model.ReportStatus;
import com.cava.telephonedirectory.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReportService {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final ReportRepository reportRepository;
    private final PersonService personService;
    private final CommunicationInfoService communicationInfoService;

    public ReportService(ReportRepository reportRepository, @Lazy PersonService personService, CommunicationInfoService communicationInfoService) {
        this.reportRepository = reportRepository;
        this.personService = personService;
        this.communicationInfoService = communicationInfoService;
    }

    public ReportDto createReport() {
        List<LocationInfoModel> locationInfoModelList = communicationInfoService.getLocationInfo();
        Date date = new Date();
        return ReportDto.builder()

                .locationInfoModelList(locationInfoModelList)
                .requestDate(date)
                .reportStatus(ReportStatus.DONE)
                .build();
    }
}
