package com.tus.PartyService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyResponse
{
    private long partyID;
    private String partyName;
    private String partyRepresentative;
}
