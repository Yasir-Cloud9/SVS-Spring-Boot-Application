package com.tus.PartyService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PARTY_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partyID;

    @Column(name = "PARTY_NAME")
    private String partyName;

    @Column(name = "PARTY_REPRESENTATIVE")
    private String partyRepresentative;
}
