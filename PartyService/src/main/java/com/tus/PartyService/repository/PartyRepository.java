package com.tus.PartyService.repository;

import com.tus.PartyService.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long>
{

}
