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
    @Query("SELECT COUNT(c) FROM Candidate c WHERE c.sex = 'Masculino' AND (c.weight / (c.height * c.height)) > 30")
    Long countObeseMen();
    @Query("SELECT COUNT(c) FROM Candidate c WHERE c.sex = 'Feminino' AND (c.weight / (c.height * c.height)) > 30")
    Long countObeseWomen();

    @Query("SELECT c.bloodType, AVG(DATEDIFF(CURRENT_DATE(), c.birthDate) / 365) " +
            "FROM Candidate c " +
            "GROUP BY c.bloodType")
    List<Object[]> getAverageAgeByBloodType();

    @Query("SELECT c.bloodType, COUNT(c) " +
            "FROM Candidate c " +
            "WHERE EXTRACT(YEAR FROM c.birthDate) <= EXTRACT(YEAR FROM CURRENT_DATE) - 16 " +
            "AND EXTRACT(YEAR FROM c.birthDate) >= EXTRACT(YEAR FROM CURRENT_DATE) - 69 " +
            "AND c.weight > 50 " +
            "AND (" +
            "   (c.bloodType = 'A+' AND c.bloodType IN ('AB+', 'A+', 'A-', 'O+', 'O-')) " +
            "   OR (c.bloodType = 'A-' AND c.bloodType IN ('A-', 'O-')) " +
            "   OR (c.bloodType = 'B+' AND c.bloodType IN ('B+', 'AB+', 'B-', 'O+', 'O-')) " +
            "   OR (c.bloodType = 'B-' AND c.bloodType IN ('B-', 'O-')) " +
            "   OR (c.bloodType = 'AB+' AND c.bloodType = 'AB+') " +
            "   OR (c.bloodType = 'AB-' AND c.bloodType IN ('AB+', 'AB-')) " +
            "   OR (c.bloodType = 'O+' AND c.bloodType IN ('A+', 'B+', 'O+', 'AB+')) " +
            "   OR (c.bloodType = 'O-' AND c.bloodType IN ('A+', 'B+', 'O+', 'AB+', 'A-', 'B-', 'O-', 'AB-'))" +
            ") " +
            "GROUP BY c.bloodType")
    List<Object[]> countDonorsByBloodType();
}


