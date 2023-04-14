package com.tus.VoterService.service;

import com.tus.VoterService.model.VoterRequest;
import com.tus.VoterService.model.VoterResponse;

public interface VoterService
{

    long addVoter(VoterRequest voterRequest);

    VoterResponse getVoterByID(long voterID);

    String resetAllVoteStatus();

    String setVoteStatus(long voterID);
}
