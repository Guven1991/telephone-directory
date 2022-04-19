package com.cava.telephonedirectory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonCountLocationModel {

    private String informationContent;

    private Long personCount;
//
//    @OneToOne
//    @JoinColumn(name="report_id", nullable=false)
//    private Report report;
}
