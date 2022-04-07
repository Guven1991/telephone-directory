package com.cava.telephonedirectory.dto;

import com.cava.telephonedirectory.model.CommunicationInformationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationInfoDto {

    private Long id;

    private CommunicationInformationType communicationInformationType;

    private String informationContent;

    private PersonDto person;

}
