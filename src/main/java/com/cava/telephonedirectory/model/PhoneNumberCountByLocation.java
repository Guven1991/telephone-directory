package com.cava.telephonedirectory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneNumberCountByLocation {

    private String informationContent;

    private Long countByPhoneNumber;
//
//    @OneToOne
//    @JoinColumn(name="report_id", nullable=false)
//    private Report report;
}
