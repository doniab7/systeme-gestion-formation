package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Override
    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    Optional<Participant> findById(@NonNull Long id);

}
