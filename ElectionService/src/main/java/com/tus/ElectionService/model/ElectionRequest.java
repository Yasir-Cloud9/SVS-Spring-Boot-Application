package com.tus.ElectionService.model;

import lombok.Data;

@Data
public class ElectionRequest
{
    private long partyID;
    private long voterID;
}