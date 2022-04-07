package com.cava.telephonedirectory.repository;

import com.cava.telephonedirectory.entity.CommunicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunicationInfoRepository extends JpaRepository<CommunicationInfo, Long> {

List<CommunicationInfo> getCommunicationInfosByPerson_Id(Long id);
}
