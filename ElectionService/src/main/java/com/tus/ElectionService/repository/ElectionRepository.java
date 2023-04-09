package com.tus.ElectionService.repository;

import com.tus.ElectionService.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long>
{

}
