package com.wk.java_test.repository;

import com.wk.java_test.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    @Query("SELECT a.state AS estado, COUNT(c.id) AS quantidade FROM Candidate c JOIN c.address a GROUP BY a.state")
    List<Object[]> getCandidatesByState();

    @Query("SELECT (TRUNC((CAST(EXTRACT(YEAR FROM CURRENT_DATE()) AS integer) - CAST(EXTRACT(YEAR FROM c.birthDate) AS integer)) / 10)) * 10 AS faixaIdade, AVG(c.weight / (c.height * c.height)) AS imcMedio " +
            "FROM Candidate c " +
            "GROUP BY (TRUNC((CAST(EXTRACT(YEAR FROM CURRENT_DATE()) AS integer) - CAST(EXTRACT(YEAR FROM c.birthDate) AS integer)) / 10)) * 10 " +
            "ORDER BY faixaIdade ASC")
    List<Object[]> getImcMediaByAgeGroup();
}


