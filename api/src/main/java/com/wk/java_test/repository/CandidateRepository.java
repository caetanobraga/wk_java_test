package com.wk.java_test.repository;

import com.wk.java_test.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    @Query("SELECT a.state AS estado, COUNT(c.id) AS quantidade FROM Candidate c JOIN c.address a GROUP BY a.state")
    List<Object[]> getCandidatesByState();
}
