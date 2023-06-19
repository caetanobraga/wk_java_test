package com.wk.java_test.repository;

import com.wk.java_test.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
}
