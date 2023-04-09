package com.tus.VoterService.service;

import com.tus.VoterService.entity.Voter;
import com.tus.VoterService.exception.VoterServiceCustomException;
import com.tus.VoterService.model.VoterRequest;
import com.tus.VoterService.model.VoterResponse;
import com.tus.VoterService.repository.VoterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class VoterServiceImpl implements VoterService
{
    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    CheckVoterAgeAndGender checkVoterAgeAndGender;

    @Override
    public long addVoter(VoterRequest voterRequest) {
        log.info("Adding Voter");

        checkVoterAgeAndGender.checkVoterAgeAndGender(voterRequest);

        Voter voter = Voter.builder()
                .voterName(voterRequest.getVoterName())
                .voterGender(voterRequest.getVoterGender())
                .voterParentName(voterRequest.getVoterParentName())
                .voterPlaceOfBirth(voterRequest.getVoterPlaceOfBirth())
                .voterDateOfBirth(voterRequest.getVoterDateOfBirth())
                .build();

        voterRepository.save(voter);
        log.info("Voter Added");

        String updatedPhotoData = "C:\\Users\\yasir\\Masters\\Self Study\\Study\\Spring boot Course\\" + voter.getVoterID() + ".jpg";
        Boolean initialVoterVoted = false;
        voter.setVoterPhotoData(updatedPhotoData);
        voter.setVoterVoted(initialVoterVoted);
        voterRepository.save(voter);
        log.info("Voter Photo Information Updated");

        return voter.getVoterID();
    }

    @Override
    public VoterResponse getVoterByID(long voterID) {
        log.info("Get the voter for the voter ID: {}", voterID);

        Voter voter = voterRepository.findById(voterID)
                .orElseThrow(() -> new VoterServiceCustomException("Voter with given ID not found in the database.", "VOTER_NOT_FOUND"));

        VoterResponse voterResponse = new VoterResponse();
        copyProperties(voter, voterResponse);
        return voterResponse;
    }

    @Override
    public String resetVoteStatus() {
        List<Voter> voters = voterRepository.findAll();
        for (Voter voter : voters) {
            voter.setVoterVoted(false);
        }
        voterRepository.saveAll(voters);
        log.info("Voter status of vote casted is reset successfully.");
        return "Voter status of vote casted is reset successfully.";
    }
}
