package com.tus.ElectionService.external.client;

import com.tus.VoterService.entity.Voter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "VOTER-SERVICE/v1/voter")
public interface VoterServiceClient
{
    @GetMapping("/{id}")
    Voter getVoterByID(@PathVariable("id") long voterID);

    @PutMapping("/set-vote-status/{id}")
    ResponseEntity<String> setVoteStatus(@PathVariable("id") long voterID);

}
