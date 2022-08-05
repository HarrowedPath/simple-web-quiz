package com.example.simplewebquiz.service;

import com.example.simplewebquiz.domain.Solve;
import com.example.simplewebquiz.repository.SolveRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class SolveService {
    SolveRepository solveRepository;

    public void save(Solve solve) {
        solveRepository.save(solve);
    }

    public Page<Solve> findAll(String userId, int pageNumber,
                               int pageSize, String sortBy) {
        val paging = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(sortBy).descending());
        return solveRepository.findAll(userId, paging);
    }
}
