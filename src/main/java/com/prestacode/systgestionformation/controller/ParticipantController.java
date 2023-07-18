package com.prestacode.systgestionformation.controller;


import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private final ParticipantService participantService;

@Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Participant>> getAllParticipants(){
        List<Participant> participants = participantService.findAllParticipants();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable("id") Long id){
        Participant participant = participantService.findParticipantById(id);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant) {
        Participant newParticipant = participantService.addParticipant(participant);
        return new ResponseEntity<>(newParticipant, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Participant> updateParticipant(@RequestBody Participant participant) {
        Participant updatedParticipant = participantService.updateParticipant(participant);
        return new ResponseEntity<>(updatedParticipant , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable("id") Long id) {
        participantService.deleteParticipant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
