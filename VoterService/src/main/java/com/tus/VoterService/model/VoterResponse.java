package com.tus.VoterService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoterResponse
{
    private long voterID;
    private String voterName;
    private String voterGender;
    private String voterParentName;
    private String voterPlaceOfBirth;
    private Date voterDateOfBirth;
    private String voterPhotoData;
    private Boolean voterVoted;

}
