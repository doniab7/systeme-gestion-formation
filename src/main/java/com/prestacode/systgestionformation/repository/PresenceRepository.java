package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    @Override
    @NonNull
    Optional<Presence> findById(@NonNull Long id);

    @Override
    void deleteById(@NonNull Long id);

    @Query("SELECT p FROM Presence p WHERE p.seance.id = :id")
    List<Presence> findBySeanceId(Long id);


    @Query("SELECT p FROM Presence p WHERE p.participant.id = :id")
    List<Presence> findByParticipantId(Long id);


}
