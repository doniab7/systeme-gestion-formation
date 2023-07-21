package com.prestacode.systgestionformation.service;


import com.prestacode.systgestionformation.exception.FormationNotFoundException;
import com.prestacode.systgestionformation.exception.ModuleNotFoundException;
import com.prestacode.systgestionformation.model.Formation;
import com.prestacode.systgestionformation.model.Module;
import com.prestacode.systgestionformation.repository.FormationRepository;
import com.prestacode.systgestionformation.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormationService {

    private final FormationRepository formationRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public FormationService(FormationRepository formationRepository, ModuleRepository moduleRepository) {
        this.formationRepository = formationRepository;
        this.moduleRepository = moduleRepository;
    }

    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }


    public Formation getFormationById(Long formationId) {
        return formationRepository.findById(formationId)
                .orElseThrow( () -> new FormationNotFoundException("Formation by id " + formationId + " was not found"));
    }



    public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public Formation updateFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }


}
