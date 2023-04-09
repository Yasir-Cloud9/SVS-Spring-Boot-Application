package com.tus.ElectionService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElectionResponse
{
    private long partyID;
    private String partyName;
    private String partyRepresentative;
    private int votesReceived;
}