package com.cava.telephonedirectory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Long id;

    private String username;

    private String lastname;

    private String company;

    private List<CommunicationInfoDto> communicationInfoList;

}
