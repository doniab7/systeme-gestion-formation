package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.*;
import com.prestacode.systgestionformation.model.Formation;
import com.prestacode.systgestionformation.model.Module;
import com.prestacode.systgestionformation.model.Paiement;
import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.repository.FormationRepository;
import com.prestacode.systgestionformation.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final FormationRepository formationRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, FormationRepository formationRepository) {
        this.moduleRepository = moduleRepository;
        this.formationRepository = formationRepository;
    }

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module getModuleById(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .orElseThrow( () -> new ModuleNotFoundException("Module by id " + moduleId + " was not found" ));
    }


    public List<Module> getAllModulesForFormation(Long formationId) {
        Optional<Formation> optionalFormation = formationRepository.findById(formationId);
        if (optionalFormation.isPresent()){
            return moduleRepository.findByFormationId(formationId);
        }
        else {
            throw new FormationNotFoundException("Formation by id " + formationId + " was not found" );
        }
    }


    public Module addModule(Long formationId, Module module) {
        Optional<Formation> optionalFormation = formationRepository.findById(formationId);
        if (optionalFormation.isPresent()) {
            Formation formation = optionalFormation.get();
            module.setFormation(formation);
            return moduleRepository.save(module);
        } else {
            throw new FormationNotFoundException("Formation by id " + formationId + " was not found" );
        }
    }


    public Module updateModule(Module module) {
        Long moduleId = module.getId();
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if (optionalModule.isPresent()){
            Module oldModule = optionalModule.get();
            module.setFormation(oldModule.getFormation());
            return moduleRepository.save(module);
        }
        else {
            throw new SessionNotFoundException("module with id " + moduleId + " is not found");
        }
    }


    public void deleteModule(Long moduleId) {
        moduleRepository.deleteById(moduleId);
    }
}
