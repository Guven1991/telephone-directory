package com.cava.telephonedirectory.request;

import com.cava.telephonedirectory.model.ReportStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReportRequest {

    private String location;

    private Integer personCount;

    private Integer phoneNumberCount;

    private LocalDateTime requestDate;

    private ReportStatus reportStatus;
}
