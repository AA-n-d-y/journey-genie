package com.genie.journey_genie.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByRouteId(Long routeId);
    Note findById(long id);
    void deleteById(Long id);
}
