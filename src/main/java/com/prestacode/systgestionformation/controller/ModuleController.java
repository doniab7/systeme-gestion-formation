package com.prestacode.systgestionformation.controller;

import com.prestacode.systgestionformation.model.Module;
import com.prestacode.systgestionformation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {

    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    //GET all modules
    @GetMapping("/all")
    public ResponseEntity<List<Module>> getAllModules(){
        List<Module> modules = moduleService.getAllModules();
        return new ResponseEntity<>(modules , HttpStatus.OK);
    }

    // GET module with a specific id
    @GetMapping("/find/{moduleId}")
    public ResponseEntity<Module> getModuleById(@PathVariable("moduleId") Long moduleId) {
        Module module = moduleService.getModuleById(moduleId);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    // GET all modules for a specific formateur
    @GetMapping("/formateur/{formateurId}")
    public ResponseEntity<List<Module>> getAllModulesForFormateur(@PathVariable("formateurId") Long formateurId) {
        List<Module> modules = moduleService.getAllModulesForFormateur(formateurId);
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    // GET all modules for a specific session
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Module>> getAllModulesForSession(@PathVariable("sessionId") Long sessionId) {
        List<Module> modules = moduleService.getAllModulesForSession(sessionId);
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }


    // ADD a module for a specific session
    //TODO: add a formateur as a parameter
    @PostMapping("/add/{sessionId}")
    public ResponseEntity<Module> addModuleForSession(@PathVariable("sessionId") Long sessionId, @RequestBody Module module) {
        Module createdModule = moduleService.addModuleForSession(sessionId, module);
        return new ResponseEntity<>(createdModule, HttpStatus.CREATED);
    }


    // UPDATE a module
    @PutMapping("/update")
    public ResponseEntity<Module> updateModule(@RequestBody Module module) {
        Module updatedModule = moduleService.updateModule(module);
        return new ResponseEntity<>(updatedModule , HttpStatus.OK);
    }

    // DELETE a module
    @DeleteMapping("/delete/{moduleId}")
    public ResponseEntity<?> deleteModule(@PathVariable("moduleId") Long moduleId) {
        moduleService.deleteModule(moduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
