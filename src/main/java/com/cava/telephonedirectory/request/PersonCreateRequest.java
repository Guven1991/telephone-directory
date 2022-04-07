package com.cava.telephonedirectory.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonCreateRequest {

    private String username;

    private String lastname;

    private String company;

}
