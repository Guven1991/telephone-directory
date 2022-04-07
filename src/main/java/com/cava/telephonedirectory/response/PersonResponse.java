package com.cava.telephonedirectory.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonResponse {

    private Long id;

    private String username;

    private String lastname;

    private String company;

}
