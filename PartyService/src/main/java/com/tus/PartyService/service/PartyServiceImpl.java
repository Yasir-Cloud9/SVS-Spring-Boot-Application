package com.tus.PartyService.service;

import com.tus.PartyService.entity.Party;
import com.tus.PartyService.exception.PartyServiceCustomException;
import com.tus.PartyService.model.PartyRequest;
import com.tus.PartyService.model.PartyResponse;
import com.tus.PartyService.repository.PartyRepository;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
@Builder
public class PartyServiceImpl implements PartyService
{
    @Autowired
    private PartyRepository partyRepository;


    @Override
    public long addParty(PartyRequest partyRequest)
    {
        log.info("Adding Party.");

        Party party = Party.builder()
                .partyName(partyRequest.getPartyName())
                .partyRepresentative(partyRequest.getPartyRepresentative())
                .build();

        partyRepository.save(party);

        log.info("Party Added.");

        return party.getPartyID();
    }

    @Override
    public PartyResponse getPartyByID(long partyID) {
        log.info("Get the party for the party ID: {}", partyID);

        Party party = partyRepository.findById(partyID)
                .orElseThrow(() -> new PartyServiceCustomException("Party with given ID not found in the database.", "PARTY_NOT_FOUND"));

        PartyResponse partyResponse = new PartyResponse();
        copyProperties(party, partyResponse);
        return partyResponse;
    }
}
