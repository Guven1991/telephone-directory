package com.cava.telephonedirectory.response;

import com.cava.telephonedirectory.model.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReportResponse {

    private Long id;

    private String location;

    private Integer personCount;

    private Integer phoneNumberCount;

    private LocalDateTime requestDate;

    private ReportStatus reportStatus;
}
