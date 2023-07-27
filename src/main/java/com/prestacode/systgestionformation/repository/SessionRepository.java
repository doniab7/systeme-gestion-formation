package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Override
    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    Optional<Session> findById(@NonNull Long id);

}
