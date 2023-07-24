package com.prestacode.systgestionformation.controller;

import com.prestacode.systgestionformation.model.Seance;
import com.prestacode.systgestionformation.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seance")
public class SeanceController {

    private final SeanceService seanceService;

    @Autowired
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Seance>> getAllSeances(){
        List<Seance> seances = seanceService.getAllSeances();
        return new ResponseEntity<>(seances, HttpStatus.OK);
    }

    @GetMapping("/find/{seanceId}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable("seanceId") Long seanceId) {
        Seance seance = seanceService.getSeanceById(seanceId);
        return new ResponseEntity<>(seance, HttpStatus.OK);
    }

    // GET all seances for a specific session
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Seance>> getAllSeancesForSession(@PathVariable("sessionId") Long sessionId) {
        List<Seance> seances = seanceService.getAllSeancesForSession(sessionId);
        return new ResponseEntity<>(seances, HttpStatus.OK);
    }


    // Add a session for a specific formation
    @PostMapping("/add/{sessionId}")
    public ResponseEntity<Seance> addSeance(@PathVariable("sessionId") Long sessionId, @RequestBody Seance seance) {
        Seance createdSeance = seanceService.addSeance(sessionId, seance);
        return new ResponseEntity<>(createdSeance, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Seance> updateSeance(@RequestBody Seance seance) {
        Seance updatedSeance = seanceService.updateSeance(seance);
        return new ResponseEntity<>(updatedSeance , HttpStatus.OK);
    }

    // DELETE a session
    @DeleteMapping("/delete/{seanceId}")
    public ResponseEntity<?> deleteSeance(@PathVariable("seanceId") Long seanceId) {
            seanceService.deleteSeance(seanceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
