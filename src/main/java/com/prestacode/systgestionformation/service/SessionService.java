package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.SessionNotFoundException;
import com.prestacode.systgestionformation.model.Session;
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


    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow( () -> new SessionNotFoundException("Session by id " + sessionId + " was not found" ));
    }



    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session updateSession(Session session) {
        Long sessionId = session.getId();
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()){
            Session oldSession = optionalSession.get();
            session.setModules(oldSession.getModules());
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
