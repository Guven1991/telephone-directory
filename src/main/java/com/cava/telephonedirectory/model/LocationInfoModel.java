package com.cava.telephonedirectory.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationInfoModel {

    private String location;

    private Long personCount;

    private Long phoneNumberCount;

    public LocationInfoModel(String location, Long personCount) {
        this.location = location;
        this.personCount = personCount;
    }


}
