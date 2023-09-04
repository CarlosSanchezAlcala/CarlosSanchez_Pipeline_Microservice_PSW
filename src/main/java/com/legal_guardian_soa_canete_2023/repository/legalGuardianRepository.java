package com.legal_guardian_soa_canete_2023.repository;

import com.legal_guardian_soa_canete_2023.model.legalGuardian;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface legalGuardianRepository extends ReactiveCrudRepository<legalGuardian, Integer> {
}
