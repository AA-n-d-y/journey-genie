package com.genie.journey_genie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genie.journey_genie.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
