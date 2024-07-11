package com.genie.journey_genie.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Route2Repository extends JpaRepository<Route2, Long> {
    List<Route2> findByUser(User user);
}
