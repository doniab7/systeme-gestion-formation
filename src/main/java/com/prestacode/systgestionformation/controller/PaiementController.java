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

    // Add a payment for a specific participant
    // id is participant id
    @PostMapping("/add/{id}")
    public ResponseEntity<Paiement> addPaiement(@PathVariable("id") Long id, @RequestBody Paiement paiement) {
        Paiement createdPaiement = paiementService.addPaiement(id, paiement);
        return new ResponseEntity<>(createdPaiement, HttpStatus.CREATED);
    }


    // DELETE a payment
    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<?> deletePaiement(@PathVariable("paymentId") Long paymentId) {
         paiementService.deletePaiement(paymentId);
         return new ResponseEntity<>(HttpStatus.OK);
    }



}
