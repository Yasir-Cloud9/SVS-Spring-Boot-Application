package com.tus.ElectionService.service;

import com.tus.ElectionService.model.ElectionRequest;

public interface ElectionService
{

    void conductElection(ElectionRequest electionRequest);
}
