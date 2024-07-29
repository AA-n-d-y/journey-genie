package com.genie.journey_genie.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user, Sort sort);
}
