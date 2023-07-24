package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.FormationNotFoundException;
import com.prestacode.systgestionformation.exception.SessionNotFoundException;
import com.prestacode.systgestionformation.model.Formation;
import com.prestacode.systgestionformation.model.Session;
import com.prestacode.systgestionformation.repository.FormationRepository;
import com.prestacode.systgestionformation.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final FormationRepository formationRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, FormationRepository formationRepository) {
        this.sessionRepository = sessionRepository;
        this.formationRepository = formationRepository;
    }


    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow( () -> new SessionNotFoundException("Session by id " + sessionId + " was not found" ));
    }


    public List<Session> getAllSessionsForFormation(Long formationId) {
        Optional<Formation> optionalFormation = formationRepository.findById(formationId);
        if (optionalFormation.isPresent()){
            return sessionRepository.findByFormationId(formationId);
        }
        else {
            throw new FormationNotFoundException("Formation by id " + formationId + " was not found" );
        }
    }


    public Session addSession(Long formationId, Session session) {
        Optional<Formation> optionalFormation = formationRepository.findById(formationId);
        if (optionalFormation.isPresent()) {
            Formation formation = optionalFormation.get();
            session.setFormation(formation);
            return sessionRepository.save(session);
        } else {
            throw new FormationNotFoundException("Formation by id " + formationId + " was not found" );
        }
    }

    public Session updateSession(Session session) {
        Long sessionId = session.getId();
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()){
            Session oldSession = optionalSession.get();
            session.setFormation(oldSession.getFormation());
            return sessionRepository.save(session);
        }
        else{
            throw new SessionNotFoundException("session with id "+ sessionId + " is not found");
        }
    }


    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }

}
