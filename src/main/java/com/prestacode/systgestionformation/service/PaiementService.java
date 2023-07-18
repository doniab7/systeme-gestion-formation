package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.PaymentAmountExceededException;
import com.prestacode.systgestionformation.exception.PaymentNotFoundException;
import com.prestacode.systgestionformation.exception.UserNotFoundException;
import com.prestacode.systgestionformation.model.Paiement;
import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.repository.PaiementRepository;
import com.prestacode.systgestionformation.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final ParticipantRepository participantRepository;
    @Autowired
    public PaiementService(PaiementRepository paiementRepository, ParticipantRepository participantRepository) {
        this.paiementRepository = paiementRepository;
        this.participantRepository = participantRepository;
    }

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }


    public Paiement getPaiementById(Long paymentId) {
        return paiementRepository.findById(paymentId)
                .orElseThrow( () -> new PaymentNotFoundException("PAYMENT by id " + paymentId + " was not found" ));
    }


    public List<Paiement> getAllPaiementsForParticipant(Long id) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isPresent()){
            Participant participant = optionalParticipant.get();
            List<Paiement> paiements = paiementRepository.findByParticipantId(id);
            float total = calculateTotal(paiements);
            float reste = participant.getMontant_total() - total;
            return paiements;
        }
        else {
            throw new UserNotFoundException("User by id " + id + " was not found" );
        }

    }

    private float calculateTotal(List<Paiement> paiements) {
        float total = 0;
        for (Paiement paiement : paiements) {
            total += paiement.getMontant();
        }
        return total;
    }

    public Paiement addPaiement(Long id, Paiement paiement) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            float total = calculateTotal(participant.getPaiements());
            float reste = participant.getMontant_total() - total;
            if (paiement.getMontant() <= reste){
                paiement.setParticipant(participant);
                return paiementRepository.save(paiement);
            }
            else{
                throw new PaymentAmountExceededException("Le montant de paiement excède le montant restant pour le participant.");
            }

        } else {
            throw new UserNotFoundException("User by id " + id + " was not found" );
        }
    }



    public void deletePaiement(Long paymentId) {
        paiementRepository.deleteById(paymentId);
    }
}
