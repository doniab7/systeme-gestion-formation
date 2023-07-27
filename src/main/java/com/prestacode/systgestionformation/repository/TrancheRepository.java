package com.prestacode.systgestionformation.repository;

import com.prestacode.systgestionformation.model.Seance;
import com.prestacode.systgestionformation.model.Tranche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrancheRepository extends JpaRepository<Tranche, Long> {
    @Override
    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    Optional<Tranche> findById(@NonNull Long id);

    @Query("SELECT t FROM Tranche t WHERE t.paiement.id = :paiementId")
    List<Tranche> findByPaiementId(Long paiementId);

}
