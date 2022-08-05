package com.example.simplewebquiz.repository;

import com.example.simplewebquiz.domain.Solve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SolveRepository extends PagingAndSortingRepository<Solve, String> {
    @Query(value = "SELECT c FROM Solve c WHERE c.userEmail = :id")
    Page<Solve> findAll(@Param("id") String userId, Pageable pageable);
}

