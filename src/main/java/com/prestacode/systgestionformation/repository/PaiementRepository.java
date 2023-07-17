package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {

    @Override
    @NonNull
    Optional<Paiement> findById(@NonNull Long id);

    @Override
    void deleteById(@NonNull Long id);
}
