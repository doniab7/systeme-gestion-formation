package com.prestacode.systgestionformation.controller;


import com.prestacode.systgestionformation.model.Formation;
import com.prestacode.systgestionformation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {

    private final FormationService formationService;

    @Autowired
    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Formation>> getAllFormations(){
        List<Formation> formations = formationService.getAllFormations();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @GetMapping("/find/{formationId}")
    public ResponseEntity<Formation> getFormationById(@PathVariable("formationId") Long formationId) {
        Formation formation = formationService.getFormationById(formationId);
        return new ResponseEntity<>(formation, HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Formation> addFormation(@RequestBody Formation formation){
        Formation newFormation = formationService.addFormation(formation);
        return new ResponseEntity<>( newFormation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public  ResponseEntity<Formation> updateFormation(@RequestBody Formation formation){
        Formation updatedFormation = formationService.updateFormation(formation);
        return new ResponseEntity<>( updatedFormation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFormation(@PathVariable("id") Long id){
        formationService.deleteFormation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
