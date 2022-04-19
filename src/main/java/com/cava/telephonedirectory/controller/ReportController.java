//package com.cava.telephonedirectory.controller;
//
//import com.cava.telephonedirectory.dto.ReportDto;
//import com.cava.telephonedirectory.request.ReportRequest;
//import com.cava.telephonedirectory.response.ReportResponse;
//import com.cava.telephonedirectory.service.PersonService;
//import com.cava.telephonedirectory.service.ReportService;
//import lombok.RequiredArgsConstructor;
//import org.dozer.DozerBeanMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/report")
//@RequiredArgsConstructor
//public class ReportController {
//    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
//
//    private final PersonService personService;
//
//    private final ReportService reportService;
//
//    @PostMapping("/person/{location}")
//            public ResponseEntity<ReportResponse> createReport (){
//            ReportDto reportDto = dozerBeanMapper.map(reportRequest,ReportDto.class);
//            ReportDto reportDtoReturned = reportService.createReport(reportDto,location);
//
//            return ResponseEntity.ok(dozerBeanMapper.map(reportDtoReturned,ReportResponse.class));
//    }
//
//
////    double result3 = myStream.filter(e -> e.getType().equals("Checking")).mapToDouble(Account::getBalance).sum();
////
////        System.out.println(result3);
//}
