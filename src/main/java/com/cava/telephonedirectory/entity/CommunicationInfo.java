package com.cava.telephonedirectory.entity;

import com.cava.telephonedirectory.model.CommunicationInformationType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "communication_info", schema = "public")
public class CommunicationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "communication_information_type")
    private CommunicationInformationType communicationInformationType;

    @Column(name = "information_content")
    private String informationContent;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

}
