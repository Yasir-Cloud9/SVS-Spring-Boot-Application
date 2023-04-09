package com.tus.VoterService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_voter")
public class Voter
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voterID;

    @Column(name = "Name")
    private String voterName;

    @Column(name = "Gender")
    private String voterGender;

    @Column(name = "Parent_Name")
    private String voterParentName;

    @Column(name = "Place_Of_Birth")
    private String voterPlaceOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth")
    private Date voterDateOfBirth;

    @Column(name = "Voter_Photo")
    private String voterPhotoData;

    @Column(name = "Voter_Voted")
    private Boolean voterVoted;

}
