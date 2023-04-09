package com.tus.PartyService.controller;

import com.tus.PartyService.model.PartyRequest;
import com.tus.PartyService.model.PartyResponse;
import com.tus.PartyService.service.PartyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/party")
@Log4j2
public class PartyController
{
    @Autowired
    private PartyService partyService;

    @PostMapping()
    public ResponseEntity<Long> addParty(@RequestBody PartyRequest partyRequest)
    {
        long partyID = partyService.addParty(partyRequest);
        return new ResponseEntity<>(partyID, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyResponse> getPartyByID(@PathVariable("id") long partyID)
    {
        PartyResponse partyResponse = partyService.getPartyByID(partyID);
        return new ResponseEntity<>(partyResponse, HttpStatus.OK);
    }
}
