package com.tus.VoterService.controller;

import com.tus.VoterService.model.VoterRequest;
import com.tus.VoterService.model.VoterResponse;
import com.tus.VoterService.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/voter")
public class VoterController
{
    @Autowired
    private VoterService voterService;

    @PostMapping()
    public ResponseEntity<Long> addVoter(@RequestBody VoterRequest voterRequest)
    {
        long voterID = voterService.addVoter(voterRequest);
        return new ResponseEntity<>(voterID, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterResponse> getVoterByID(@PathVariable("id") long voterID)
    {
        VoterResponse voterResponse = voterService.getVoterByID(voterID);
        return new ResponseEntity<>(voterResponse, HttpStatus.OK);
    }

    @PutMapping("/reset-all-vote-status")
    public ResponseEntity<String> resetAllVoteStatus()
    {
        voterService.resetAllVoteStatus();
        return new ResponseEntity<>("Voter status of vote casted is reset successfully.", HttpStatus.OK);
    }

    @PutMapping("/set-vote-status/{id}")
    public ResponseEntity<String> setVoteStatus(@PathVariable("id") long voterID)
    {
        String voterOperationStatus = voterService.setVoteStatus(voterID);
        return new ResponseEntity<>(voterOperationStatus, HttpStatus.OK);
    }
}
