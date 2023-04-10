package com.example.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController
{
    @GetMapping("/voterServiceFallBack")
    public String voterServiceFallback()
    {
        return "Voter Service is down, please check it and try again later.";
    }

    @GetMapping("/partyServiceFallBack")
    public String partyServiceFallback()
    {
        return "Political Party Service is down, please check it and try again later.";
    }

    @GetMapping("/faceRecogServiceFallBack")
    public String faceRecogServiceFallback()
    {
        return "Face Recognition Service is down, please check it and try again later.";
    }

    @GetMapping("/electionServiceFallBack")
    public String electionServiceFallback()
    {
        return "Election Service is down, please check it and try again later.";
    }
}
