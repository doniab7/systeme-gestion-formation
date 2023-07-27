package com.prestacode.systgestionformation.controller;

import com.prestacode.systgestionformation.model.Paiement;
import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.model.Presence;
import com.prestacode.systgestionformation.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presence")
public class PresenceController {

    private final PresenceService presenceService;

    @Autowired
    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    //GET all presences
    @GetMapping("/all")
    public ResponseEntity<List<Presence>> getAllPresences() {
        List<Presence> presences = presenceService.getAllPresences();
        return new ResponseEntity<>(presences, HttpStatus.OK);
    }

    //GET presence by id
    @GetMapping("/find/{presenceId}")
    public ResponseEntity<Presence> getPresenceById(@PathVariable("presenceId") Long presenceId) {
        Presence presence = presenceService.getPresenceById(presenceId);
        return new ResponseEntity<>(presence, HttpStatus.OK);
    }


    //GET presences for a specific participant
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<Presence>> getAllPresencesForParticipant(@PathVariable("participantId") Long participantId) {
        List<Presence> presences = presenceService.getAllPresencesForParticipant(participantId);
        return new ResponseEntity<>(presences, HttpStatus.OK);
    }



    //GET presences for a specific séance
    @GetMapping("/seance/{seanceId}")
    public ResponseEntity<List<Presence>> getAllPresencesForSeance(@PathVariable("seanceId") Long seanceId) {
        List<Presence> presences = presenceService.getAllPresencesForSeance(seanceId);
        return new ResponseEntity<>(presences, HttpStatus.OK);
    }


    //UPDATE a presence (change present or absent)
    @PutMapping("/update")
    public ResponseEntity<Presence> updatePresence(@RequestBody Presence presence) {
        Presence updatedPresence = presenceService.updatePresence(presence);
        return new ResponseEntity<>(updatedPresence , HttpStatus.OK);
    }

}
