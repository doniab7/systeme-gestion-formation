package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.PaymentAmountExceededException;
import com.prestacode.systgestionformation.exception.PaymentNotFoundException;
import com.prestacode.systgestionformation.exception.TrancheNotFoundException;
import com.prestacode.systgestionformation.exception.UserNotFoundException;
import com.prestacode.systgestionformation.model.EtatPaiement;
import com.prestacode.systgestionformation.model.Formateur;
import com.prestacode.systgestionformation.model.Paiement;
import com.prestacode.systgestionformation.model.Tranche;
import com.prestacode.systgestionformation.repository.PaiementRepository;
import com.prestacode.systgestionformation.repository.TrancheRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrancheService {

    private final TrancheRepository trancheRepository;
    private final PaiementRepository paiementRepository;

    @Autowired
    public TrancheService(TrancheRepository trancheRepository, PaiementRepository paiementRepository) {
        this.trancheRepository = trancheRepository;
        this.paiementRepository = paiementRepository;
    }

    public List<Tranche> getAllTranches() {
        return trancheRepository.findAll();
    }


    public Tranche getTrancheById(Long trancheId) {
        return trancheRepository.findById(trancheId)
                .orElseThrow( () -> new TrancheNotFoundException("tranche by id " + trancheId + " was not found" ));
    }


    public List<Tranche> getAllTranchesForPaiement(Long paiementId) {
        Optional<Paiement> optionalPaiement = paiementRepository.findById(paiementId);
        if (optionalPaiement.isPresent()){
            Paiement paiement = optionalPaiement.get();
            List<Tranche> tranches = trancheRepository.findByPaiementId(paiementId);
            float total = calculateTotal(tranches);
            float reste = paiement.getMontantTotal() - total;
            return tranches;
        }
        else {
            throw new PaymentNotFoundException("paiement by id " + paiementId + " was not found" );
        }
    }

    private float calculateTotal(List<Tranche> tranches) {
        float total = 0;
        for (Tranche tranche : tranches) {
            total += tranche.getMontant();
        }
        return total;
    }

    public Tranche addTranche(Long paiementId, Tranche tranche) {
        Optional<Paiement> optionalPaiement = paiementRepository.findById(paiementId);
        if (optionalPaiement.isPresent()) {
            Paiement paiement = optionalPaiement.get();
            float total = calculateTotal(paiement.getTranches());
            float reste = paiement.getMontantTotal() - total;
            if (tranche.getMontant() <= reste){
                if (tranche.getMontant() == reste)  paiement.setEtatPaiement(EtatPaiement.PAYE);
                else paiement.setEtatPaiement(EtatPaiement.TRANCHE);
                tranche.setPaiement(paiement);
                return trancheRepository.save(tranche);
            }
            else{
                throw new PaymentAmountExceededException("Le montant de paiement excède le montant restant pour le participant.");
            }

        } else {
            throw new PaymentNotFoundException("payment by id " + paiementId + " was not found" );
        }
    }


    public void deleteTranche(Long trancheId) {
        trancheRepository.deleteById(trancheId);
    }

}
