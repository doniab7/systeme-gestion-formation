package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.PaymentNotFoundException;
import com.prestacode.systgestionformation.exception.SessionNotFoundException;
import com.prestacode.systgestionformation.exception.UserNotFoundException;
import com.prestacode.systgestionformation.model.*;
import com.prestacode.systgestionformation.model.Module;
import com.prestacode.systgestionformation.repository.PaiementRepository;
import com.prestacode.systgestionformation.repository.ParticipantRepository;
import com.prestacode.systgestionformation.repository.SessionRepository;
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
    private final SessionRepository sessionRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository, ParticipantRepository participantRepository, SessionRepository sessionRepository) {
        this.paiementRepository = paiementRepository;
        this.participantRepository = participantRepository;
        this.sessionRepository = sessionRepository;
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
            return paiementRepository.findByParticipantId(id);
        }
        else {
            throw new UserNotFoundException("User by id " + id + " was not found" );
        }
    }

    //inscription dun participant dans une session
    public Paiement addPaiement(Long participantId, Long sessionId, float montant) {
        Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if( (optionalParticipant.isPresent()) && (optionalSession.isPresent())) {
            Paiement paiement = new Paiement();

            // participant
            Participant participant = optionalParticipant.get();
            paiement.setParticipant(participant);
            participant.getPaiements().add(paiement);

            //session
            Session session = optionalSession.get();
            paiement.setSession(session);
            session.getPaiements().add(paiement);

            // add participant to presence table
            List<Module> modules = session.getModules();
            for (Module module : modules){
                List<Seance> seances = module.getSeances();
                for (Seance seance: seances){
                    Presence presence = new Presence();
                    presence.setPresent(false);
                    presence.setSeance(seance);
                    seance.getPresences().add(presence);
                    presence.setParticipant(participant);
                    participant.getPresences().add(presence);
                }
            }

                paiement.setMontantTotal(montant);
            paiement.setEtatPaiement(EtatPaiement.NONPAYE);
            return paiementRepository.save(paiement);

        } else  if (optionalParticipant.isPresent()){
            throw new SessionNotFoundException("Session by id " + sessionId + " was not found" );
        }
        else{
            throw new UserNotFoundException("User by id " + participantId + " was not found");
        }
    }



    public void deletePaiement(Long paymentId) {
        paiementRepository.deleteById(paymentId);
    }

    public List<Paiement> getAllPaiementsForSession(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()){
            return paiementRepository.findBySessionId(sessionId);
        }
        else {
            throw new SessionNotFoundException("Session by id " + sessionId + " was not found" );
        }
    }

}
