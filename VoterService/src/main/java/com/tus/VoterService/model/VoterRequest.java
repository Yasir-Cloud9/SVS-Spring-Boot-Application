package com.tus.VoterService.model;

import lombok.Data;

import java.util.Date;


@Data
public class VoterRequest
{
    private String voterName;
    private String voterGender;
    private String voterParentName;
    private String voterPlaceOfBirth;
    private Date voterDateOfBirth;
    private String voterPhotoData;
    private Boolean voterVoted;

}
