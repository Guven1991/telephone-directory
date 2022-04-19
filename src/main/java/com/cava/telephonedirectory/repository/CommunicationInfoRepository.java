package com.cava.telephonedirectory.repository;

import com.cava.telephonedirectory.entity.CommunicationInfo;
import com.cava.telephonedirectory.model.CommunicationInformationType;
import com.cava.telephonedirectory.model.PersonCountLocationModel;
import com.cava.telephonedirectory.model.PhoneNumberCountByLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunicationInfoRepository extends JpaRepository<CommunicationInfo, Long> {

    List<CommunicationInfo> getCommunicationInfosByPerson_Id(Long id);

    List<CommunicationInfo> getCommunicationInfosByCommunicationInformationTypeAndInformationContent(CommunicationInformationType type, String content);


    @Query(value = "SELECT new com.cava.telephonedirectory.model.PersonCountLocationModel(c.informationContent," +
            " count(c.informationContent)) FROM CommunicationInfo c WHERE c.communicationInformationType='LOCATION' GROUP BY c.informationContent")
    List<PersonCountLocationModel> getLocationCount();

    @Query(value = "SELECT new com.cava.telephonedirectory.model.PhoneNumberCountByLocation(c.informationContent," +
            " count(c.informationContent)) FROM CommunicationInfo c WHERE c.communicationInformationType='PHONE_NUMBER'GROUP BY c.informationContent")
    List<PhoneNumberCountByLocation> getPhoneNumberCount();


//    SELECT
//            information_content,
//    COUNT(*)
//    FROM
//    communication_info where communication_information_type='LOCATION'
//    GROUP BY information_content;

//    @Query("SELECT c.year, COUNT(c.year) FROM Comment AS c GROUP BY c.year ORDER BY c.year DESC")
//List<Object[]> countTotalCommentsByYear();
}
