package com.tus.ElectionService.service;

import com.tus.ElectionService.entity.Election;
import com.tus.ElectionService.external.client.FaceAuthenticationServiceClient;
import com.tus.ElectionService.external.client.PartyServiceClient;
import com.tus.ElectionService.external.client.VoterServiceClient;
import com.tus.ElectionService.model.ElectionRequest;
import com.tus.ElectionService.repository.ElectionRepository;
import com.tus.PartyService.entity.Party;
import com.tus.VoterService.entity.Voter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ElectionServiceImpl implements ElectionService
{
    private ElectionRepository electionRepository;
    private PartyServiceClient partyServiceClient;
    private VoterServiceClient voterServiceClient;
    private  FaceAuthenticationServiceClient faceAuthenticationServiceClient;

    public ElectionServiceImpl(ElectionRepository electionRepository, PartyServiceClient partyServiceClient,
                               VoterServiceClient voterServiceClient, FaceAuthenticationServiceClient faceAuthenticationServiceClient) {
        this.electionRepository = electionRepository;
        this.partyServiceClient = partyServiceClient;
        this.voterServiceClient = voterServiceClient;
        this.faceAuthenticationServiceClient = faceAuthenticationServiceClient;
    }

    @Override
    public String conductElection(ElectionRequest electionRequest)
    {
        Party party = partyServiceClient.getPartyByID(electionRequest.getPartyID());
        Voter voter = voterServiceClient.getVoterByID(electionRequest.getVoterID());
        String faceSimilarity = faceAuthenticationServiceClient.faceRecognition(voter.getVoterID().toString());
        Optional<Election> optionalElection = electionRepository.findById(electionRequest.getPartyID());

        if (voter.getVoterVoted() == true)
        {
            log.info("Voter with ID {} can not vote as they have already voted.", electionRequest.getVoterID());
            return "Voter with ID " + electionRequest.getVoterID() + " can not vote as they have already voted.";
        }
        if (faceSimilarity.equals("False"))
        {
            log.info("Voter can not vote as face authentication is failed.");
            return "Voter can not vote as face authentication is failed.";
        }
        else if (faceSimilarity.equals("Error"))
        {
            log.info("Voter can not vote as the system is unable to process the face authentication request.");
            return "Voter can not vote as the system is unable to process the face authentication request.";
        }

        if (optionalElection.isPresent())
        {
            Election election = optionalElection.get();
            election.setVotesReceived(election.getVotesReceived() + 1);
            electionRepository.save(election);
            voterServiceClient.setVoteStatus(voter.getVoterID());
            log.info("Vote casted for an existing party in database.");
            return "Vote casted for an existing party in database.";
        }
        else
        {
            Election electionNew = new Election();
            electionNew.setPartyID(party.getPartyID());
            electionNew.setPartyName(party.getPartyName());
            electionNew.setPartyRepresentative(party.getPartyRepresentative());
            electionNew.setVotesReceived(1);
            electionRepository.save(electionNew);
            voterServiceClient.setVoteStatus(voter.getVoterID());
            log.info("Vote casted for an new party in database.");
            return "Vote casted for an new party in database.";
        }
    }
}
