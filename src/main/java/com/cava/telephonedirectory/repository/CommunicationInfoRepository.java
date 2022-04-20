package com.cava.telephonedirectory.repository;

import com.cava.telephonedirectory.entity.CommunicationInfo;
import com.cava.telephonedirectory.model.CommunicationInformationType;
import com.cava.telephonedirectory.model.LocationInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunicationInfoRepository extends JpaRepository<CommunicationInfo, Long> {

    List<CommunicationInfo> getCommunicationInfosByPerson_Id(Long id);

    List<CommunicationInfo> getCommunicationInfosByCommunicationInformationTypeAndInformationContent(CommunicationInformationType type, String content);

    @Query(value = "SELECT new com.cava.telephonedirectory.model.LocationInfoModel(c.informationContent," +
            " count(c.informationContent)) FROM CommunicationInfo c WHERE c.communicationInformationType='LOCATION' GROUP BY c.informationContent")
    List<LocationInfoModel> getPersonCountByLocation();

}
