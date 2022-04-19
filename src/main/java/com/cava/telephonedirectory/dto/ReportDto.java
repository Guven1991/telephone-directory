package com.cava.telephonedirectory.dto;

import com.cava.telephonedirectory.model.PersonCountLocationModel;
import com.cava.telephonedirectory.model.ReportStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ReportDto {

    private Long id;

    private List<PersonCountLocationModel> personCountLocationModelList;

    private Integer phoneNumberCount;

    private LocalDateTime requestDate;

    private ReportStatus reportStatus;
}
