package com.tus.ElectionService.controller;

import com.tus.ElectionService.model.ElectionRequest;
import com.tus.ElectionService.service.ElectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/election")
public class ElectionController
{
    @Autowired
    private ElectionService electionService;

    @PostMapping()
    public ResponseEntity<String> conductElection(@RequestBody ElectionRequest electionRequest)
    {
        electionService.conductElection(electionRequest);
        log.info("Vote casted");
        return new ResponseEntity<>("Vote casted successfully to the party with ID : " + electionRequest.getPartyID(), HttpStatus.ACCEPTED);
    }

}
