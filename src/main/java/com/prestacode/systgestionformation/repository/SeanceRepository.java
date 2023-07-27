package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepository extends JpaRepository<Seance , Long> {
    @Override
    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    Optional<Seance> findById(@NonNull Long id);

    @Query("SELECT s FROM Seance s WHERE s.module.id = :moduleId")
    List<Seance> findByModuleId(Long moduleId);


}
