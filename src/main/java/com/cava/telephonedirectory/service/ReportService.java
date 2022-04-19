//package com.cava.telephonedirectory.service;
//
//import com.cava.telephonedirectory.dto.CommunicationInfoDto;
//import com.cava.telephonedirectory.dto.ReportDto;
//import com.cava.telephonedirectory.repository.ReportRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.dozer.DozerBeanMapper;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class ReportService {
//    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
//
//    private final ReportRepository reportRepository;
//    private final PersonService personService;
//    private final CommunicationInfoService communicationInfoService;
//
//    public ReportService(ReportRepository reportRepository, @Lazy PersonService personService, CommunicationInfoService communicationInfoService) {
//        this.reportRepository = reportRepository;
//        this.personService = personService;
//        this.communicationInfoService = communicationInfoService;
//    }
//
//    public ReportDto createReport(String location) {
//        List<CommunicationInfoDto> communicationInfoDtoList = communicationInfoService.getAllCommunicationInfo();
//        List<String> list =communicationInfoDtoList.stream().map(e -> e.getInformationContent()).collect(Collectors.toList());
//        list.forEach(System.out ::println);
//
//        return reportDto;
//
//    }
//}
