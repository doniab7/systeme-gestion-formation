package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Override
    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    Optional<Module> findById(@NonNull Long id);

    @Query("SELECT m FROM Module m WHERE m.formation.id = :formationId")
    List<Module> findByFormationId(Long formationId);
}
