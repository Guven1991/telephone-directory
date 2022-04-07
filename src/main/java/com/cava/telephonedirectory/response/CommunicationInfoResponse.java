package com.cava.telephonedirectory.response;

import com.cava.telephonedirectory.model.CommunicationInformationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunicationInfoResponse {

    private Long id;

    private CommunicationInformationType communicationInformationType;

    private String informationContent;

}
