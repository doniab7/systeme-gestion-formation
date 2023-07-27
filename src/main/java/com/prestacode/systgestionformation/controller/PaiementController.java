package com.prestacode.systgestionformation.controller;

import com.prestacode.systgestionformation.model.Paiement;
import com.prestacode.systgestionformation.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiement")
public class PaiementController {

    private final PaiementService paiementService;

    @Autowired
    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // GET all payments
    @GetMapping("/all")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        List<Paiement> paiements = paiementService.getAllPaiements();
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }

    // GET payment by payment id
    @GetMapping("/find/{paymentId}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable("paymentId") Long paymentId) {
        Paiement paiement = paiementService.getPaiementById(paymentId);
        return new ResponseEntity<>(paiement, HttpStatus.OK);
    }

    // GET all payments for a specific participant
    // id is participant id
    @GetMapping("/participant/{id}")
    public ResponseEntity<List<Paiement>> getAllPaiementsForParticipant(@PathVariable("id") Long id) {
        List<Paiement> paiements = paiementService.getAllPaiementsForParticipant(id);
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }

    //GET all payments for a specific session
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Paiement>> getAllPaiementsForSession(@PathVariable("sessionId") Long sessionId) {
        List<Paiement> paiements = paiementService.getAllPaiementsForSession(sessionId);
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }


    // Add a payment (inscrire un participant a une session)
    @PostMapping("/add")
    public ResponseEntity<Paiement> addPaiement(@RequestParam Long participantId, @RequestParam Long sessionId, @RequestParam float montant) {
        Paiement createdPaiement = paiementService.addPaiement(participantId, sessionId, montant);
        return new ResponseEntity<>(createdPaiement, HttpStatus.CREATED);
    }


    // DELETE a payment
    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<?> deletePaiement(@PathVariable("paymentId") Long paymentId) {
         paiementService.deletePaiement(paymentId);
         return new ResponseEntity<>(HttpStatus.OK);
    }



}
