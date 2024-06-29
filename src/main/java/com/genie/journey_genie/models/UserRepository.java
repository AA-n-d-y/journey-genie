package com.genie.journey_genie.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findByUsernameAndPassword(String username, String password);
}
