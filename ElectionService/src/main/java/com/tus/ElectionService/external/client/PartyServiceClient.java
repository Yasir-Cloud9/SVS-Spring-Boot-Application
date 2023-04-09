package com.tus.ElectionService.external.client;

import com.tus.PartyService.entity.Party;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PARTY-SERVICE/v1/party")
public interface PartyServiceClient
{
    @GetMapping("/{id}")
    Party getPartyByID(@PathVariable("id") long partyID);
}
