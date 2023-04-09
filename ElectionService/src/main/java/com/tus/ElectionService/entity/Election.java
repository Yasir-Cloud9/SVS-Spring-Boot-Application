package com.tus.ElectionService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ELECTION_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Election
{
    @Id
    private long partyID;

    @Column(name = "PARTY_NAME")
    private String partyName;

    @Column(name = "PARTY_REPRESENTATIVE")
    private String partyRepresentative;

    @Column(name = "VOTES_RECEIVED")
    private int votesReceived;
}
