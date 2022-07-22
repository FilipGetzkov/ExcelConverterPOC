package com.slf.data.ingestion.repository;

import com.slf.data.ingestion.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Integer> {
}
