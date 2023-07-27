package com.prestacode.systgestionformation.controller;


import com.prestacode.systgestionformation.model.Session;
import com.prestacode.systgestionformation.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Session>> getAllSessions(){
        List<Session> sessions = sessionService.getAllSessions();
        return new ResponseEntity<>(sessions , HttpStatus.OK);
    }

    @GetMapping("/find/{sessionId}")
    public ResponseEntity<Session> getSessionById(@PathVariable("sessionId") Long sessionId) {
        Session session = sessionService.getSessionById(sessionId);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }


    // Add a session
    @PostMapping("/add")
    public ResponseEntity<Session> addSession(@RequestBody Session session) {
        Session createdSession = sessionService.addSession(session);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Session> updateSession(@RequestBody Session session) {
        Session updatedSession = sessionService.updateSession(session);
        return new ResponseEntity<>(updatedSession , HttpStatus.OK);
    }


    // DELETE a session
    @DeleteMapping("/delete/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable("sessionId") Long sessionId) {
        sessionService.deleteSession(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
