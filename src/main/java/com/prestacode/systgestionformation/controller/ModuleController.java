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

    @GetMapping("/all")
    public ResponseEntity<List<Module>> getAllModules(){
        List<Module> modules = moduleService.getAllModules();
        return new ResponseEntity<>(modules , HttpStatus.OK);
    }

    @GetMapping("/find/{moduleId}")
    public ResponseEntity<Module> getModuleById(@PathVariable("moduleId") Long moduleId) {
        Module module = moduleService.getModuleById(moduleId);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    // GET all modules for a specific formation
    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Module>> getAllModulesForFormation(@PathVariable("formationId") Long formationId) {
        List<Module> modules = moduleService.getAllModulesForFormation(formationId);
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    // Add a module for a specific formation
    @PostMapping("/add/{formationId}")
    public ResponseEntity<Module> addModule(@PathVariable("formationId") Long formationId, @RequestBody Module module) {
        Module createdModule = moduleService.addModule(formationId, module);
        return new ResponseEntity<>(createdModule, HttpStatus.CREATED);
    }

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
