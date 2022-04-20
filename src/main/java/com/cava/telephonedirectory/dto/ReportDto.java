package com.cava.telephonedirectory.dto;

import com.cava.telephonedirectory.model.LocationInfoModel;
import com.cava.telephonedirectory.model.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Long id;

    private Date requestDate;

    private ReportStatus reportStatus;

    private List<LocationInfoModel> locationInfoModelList;
}
