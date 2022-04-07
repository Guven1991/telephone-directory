package com.cava.telephonedirectory.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PersonGetByIdResponse {

    private Long id;

    private String username;

    private String lastname;

    private String company;

    private List<CommunicationInfoPersonResponse> communicationInfoList;
}
