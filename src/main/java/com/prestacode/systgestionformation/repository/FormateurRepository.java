package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {

    @Override
    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    Optional<Formateur> findById(@NonNull Long id);

}
