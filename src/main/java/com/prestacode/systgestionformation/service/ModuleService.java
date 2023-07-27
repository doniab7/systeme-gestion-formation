package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.*;
import com.prestacode.systgestionformation.model.*;
import com.prestacode.systgestionformation.model.Module;
import com.prestacode.systgestionformation.repository.FormateurRepository;
import com.prestacode.systgestionformation.repository.ModuleRepository;
import com.prestacode.systgestionformation.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final FormateurRepository formateurRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, FormateurRepository formateurRepository, SessionRepository sessionRepository) {
        this.moduleRepository = moduleRepository;
        this.formateurRepository = formateurRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module getModuleById(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .orElseThrow( () -> new ModuleNotFoundException("Module by id " + moduleId + " was not found" ));
    }


    public List<Module> getAllModulesForFormateur(Long formateurId) {
        Optional<Formateur> optionalFormateur = formateurRepository.findById(formateurId);
        if (optionalFormateur.isPresent()){
            return moduleRepository.findByFormateurId(formateurId);
        }
        else {
            throw new UserNotFoundException("User by id " + formateurId + " was not found" );
        }
    }



    public Module updateModule(Module module) {
        Long moduleId = module.getId();
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if (optionalModule.isPresent()){
            Module oldModule = optionalModule.get();
            module.setFormateur(oldModule.getFormateur());
            module.setSession(oldModule.getSession());
            module.setSeances(oldModule.getSeances());
            return moduleRepository.save(module);
        }
        else{
            throw new ModuleNotFoundException("module with id "+ moduleId + " is not found");
        }
    }


    public void deleteModule(Long moduleId) {
        moduleRepository.deleteById(moduleId);
    }

    public Module addModuleForSession(Long sessionId, Module module) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            module.setSession(session);
            return moduleRepository.save(module);
        } else {
            throw new SessionNotFoundException("Session by id " + sessionId + " was not found" );
        }
    }


    public List<Module> getAllModulesForSession(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()){
            return moduleRepository.findBySessionId(sessionId);
        }
        else {
            throw new SessionNotFoundException("Session by id " + sessionId + " was not found" );
        }
    }

}
