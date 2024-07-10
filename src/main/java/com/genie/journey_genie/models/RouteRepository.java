package com.genie.journey_genie.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
       Route findById(int id);
}
