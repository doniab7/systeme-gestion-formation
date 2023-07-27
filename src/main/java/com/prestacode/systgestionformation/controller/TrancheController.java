package com.prestacode.systgestionformation.controller;


import com.prestacode.systgestionformation.model.Tranche;
import com.prestacode.systgestionformation.service.TrancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tranche")
public class TrancheController {

    private final TrancheService trancheService;

    @Autowired
    public TrancheController(TrancheService trancheService) {
        this.trancheService = trancheService;
    }

    // GET all payments
    @GetMapping("/all")
    public ResponseEntity<List<Tranche>> getAllTranches() {
        List<Tranche> tranches = trancheService.getAllTranches();
        return new ResponseEntity<>(tranches, HttpStatus.OK);
    }

    // GET tranche by tranche id
    @GetMapping("/find/{trancheId}")
    public ResponseEntity<Tranche> getTrancheById(@PathVariable("trancheId") Long trancheId) {
        Tranche tranche = trancheService.getTrancheById(trancheId);
        return new ResponseEntity<>(tranche, HttpStatus.OK);
    }

    // GET all tranches for a specific paiement

    @GetMapping("/paiement/{paiementId}")
    public ResponseEntity<List<Tranche>> getAllTranchesForPaiement(@PathVariable("paiementId") Long paiementId) {
        List<Tranche>  tranches = trancheService.getAllTranchesForPaiement(paiementId);
        return new ResponseEntity<>(tranches, HttpStatus.OK);
    }

    // ADD a tranche for a specific paiement
    @PostMapping("/add/{paiementId}")
    public ResponseEntity<Tranche> addTranche(@PathVariable("paiementId") Long paiementId, @RequestBody Tranche tranche) {
        Tranche createdTranche = trancheService.addTranche(paiementId, tranche);
        return new ResponseEntity<>(createdTranche, HttpStatus.CREATED);
    }

    // DELETE a tranche
    @DeleteMapping("/delete/{trancheId}")
    public ResponseEntity<?> deletePaiement(@PathVariable("trancheId") Long trancheId) {
        trancheService.deleteTranche(trancheId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
