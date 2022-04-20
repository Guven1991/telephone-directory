package com.cava.telephonedirectory.controller;

import com.cava.telephonedirectory.response.ReportResponse;
import com.cava.telephonedirectory.service.PersonService;
import com.cava.telephonedirectory.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final PersonService personService;

    private final ReportService reportService;

//    @PostMapping("/person/{location}")
//            public ResponseEntity<ReportResponse> createReport (){
//            ReportDto reportDto = dozerBeanMapper.map(reportRequest,ReportDto.class);
//            ReportDto reportDtoReturned = reportService.createReport(reportDto,location);
//
//            return ResponseEntity.ok(dozerBeanMapper.map(reportDtoReturned,ReportResponse.class));
//    }

    @PostMapping()
    public ResponseEntity<ReportResponse> createReport() {
        return ResponseEntity.ok(dozerBeanMapper.map(reportService.createReport(),ReportResponse.class));
    }
}
