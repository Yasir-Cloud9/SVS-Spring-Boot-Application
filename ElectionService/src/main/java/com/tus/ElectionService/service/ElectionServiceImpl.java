package com.tus.ElectionService.service;

import com.tus.ElectionService.entity.Election;
import com.tus.ElectionService.external.client.PartyServiceClient;
import com.tus.ElectionService.model.ElectionRequest;
import com.tus.ElectionService.repository.ElectionRepository;
import com.tus.PartyService.entity.Party;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ElectionServiceImpl implements ElectionService
{
    private ElectionRepository electionRepository;
    private PartyServiceClient partyServiceClient;

    public ElectionServiceImpl(ElectionRepository electionRepository, PartyServiceClient partyServiceClient) {
        this.electionRepository = electionRepository;
        this.partyServiceClient = partyServiceClient;
    }

    @Override
    public void conductElection(ElectionRequest electionRequest)
    {
        Party party = partyServiceClient.getPartyByID(electionRequest.getPartyID());
        Optional<Election> optionalElection = electionRepository.findById(electionRequest.getPartyID());

        if (optionalElection.isPresent())
        {
            Election election = optionalElection.get();
            election.setVotesReceived(election.getVotesReceived() + 1);
            electionRepository.save(election);
            log.info("Vote casted for an existing party in database.");
        }
        else
        {
            Election electionNew = new Election();
            electionNew.setPartyID(party.getPartyID());
            electionNew.setPartyName(party.getPartyName());
            electionNew.setPartyRepresentative(party.getPartyRepresentative());
            electionNew.setVotesReceived(1);
            electionRepository.save(electionNew);
            log.info("Vote casted for an new party in database.");
        }
    }
}
