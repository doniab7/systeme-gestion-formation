package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.SeanceNotFoundException;
import com.prestacode.systgestionformation.exception.SessionNotFoundException;
import com.prestacode.systgestionformation.model.Seance;
import com.prestacode.systgestionformation.model.Session;
import com.prestacode.systgestionformation.repository.SeanceRepository;
import com.prestacode.systgestionformation.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final SessionRepository sessionRepository;

    public SeanceService(SeanceRepository seanceRepository, SessionRepository sessionRepository) {
        this.seanceRepository = seanceRepository;
        this.sessionRepository = sessionRepository;
    }


    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    public Seance getSeanceById(Long seanceId) {
        return seanceRepository.findById(seanceId)
                .orElseThrow( () -> new SeanceNotFoundException("Seance by id " + seanceId + " was not found" ));
    }


    public List<Seance> getAllSeancesForSession(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()){
            return seanceRepository.findBySessionId(sessionId);
        }
        else {
            throw new SessionNotFoundException("Session by id " + sessionId + " was not found" );
        }
    }


    public Seance addSeance(Long sessionId, Seance seance) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            seance.setSession(session);
            return seanceRepository.save(seance);
        } else {
            throw new SessionNotFoundException("Session by id " + sessionId + " was not found" );
        }
    }


    public Seance updateSeance(Seance seance) {
        Long seanceId = seance.getId();
        Optional<Seance> optionalSeance = seanceRepository.findById(seanceId);
        if (optionalSeance.isPresent()){
            Seance oldSeance = optionalSeance.get();
            seance.setSession(oldSeance.getSession());
            return seanceRepository.save(seance);
        }
        else{
            throw new SeanceNotFoundException("seance with id "+ seanceId + " is not found");
        }
    }


    public void deleteSeance(Long seanceId) {
        seanceRepository.deleteById(seanceId);
    }
}
