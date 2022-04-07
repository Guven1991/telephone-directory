package com.cava.telephonedirectory.repository;

import com.cava.telephonedirectory.entity.CommunicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationInfoRepository extends JpaRepository<CommunicationInfo, Long> {
}
