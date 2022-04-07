package com.cava.telephonedirectory.request;

import com.cava.telephonedirectory.model.CommunicationInformationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunicationInfoCreateRequest {

    private CommunicationInformationType communicationInformationType;

    private String informationContent;
}
