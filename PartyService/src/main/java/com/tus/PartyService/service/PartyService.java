package com.tus.PartyService.service;

import com.tus.PartyService.model.PartyRequest;
import com.tus.PartyService.model.PartyResponse;

public interface PartyService
{

    long addParty(PartyRequest partyRequest);

    PartyResponse getPartyByID(long partyID);
}
