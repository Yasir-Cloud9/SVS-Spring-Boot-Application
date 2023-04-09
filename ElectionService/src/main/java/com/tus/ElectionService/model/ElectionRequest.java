package com.tus.ElectionService.model;

import lombok.Data;

@Data
public class ElectionRequest
{
    private long partyID;
    private String partyName;
    private String partyRepresentative;
    private int votesReceived;
}