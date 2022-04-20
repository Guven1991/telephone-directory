package com.cava.telephonedirectory.response;

import com.cava.telephonedirectory.model.LocationInfoModel;
import com.cava.telephonedirectory.model.ReportStatus;
import lombok.*;

import java.util.Date;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportResponse {

    private Long id;

    private Date requestDate;

    private ReportStatus reportStatus;

    private List<LocationInfoModel> locationInfoModelList;
}
