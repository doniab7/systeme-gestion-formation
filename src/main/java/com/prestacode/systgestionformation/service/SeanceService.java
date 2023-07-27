package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.ModuleNotFoundException;
import com.prestacode.systgestionformation.exception.SeanceNotFoundException;
import com.prestacode.systgestionformation.model.*;
import com.prestacode.systgestionformation.model.Module;
import com.prestacode.systgestionformation.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final ModuleRepository moduleRepository;
    private final PaiementRepository paiementRepository;
    private final PresenceRepository presenceRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public SeanceService(SeanceRepository seanceRepository, ModuleRepository moduleRepository, PaiementRepository paiementRepository, PresenceRepository presenceRepository, ParticipantRepository participantRepository) {
        this.seanceRepository = seanceRepository;
        this.moduleRepository = moduleRepository;
        this.paiementRepository = paiementRepository;
        this.presenceRepository = presenceRepository;
        this.participantRepository = participantRepository;
    }


    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    public Seance getSeanceById(Long seanceId) {
        return seanceRepository.findById(seanceId)
                .orElseThrow( () -> new SeanceNotFoundException("Seance by id " + seanceId + " was not found" ));
    }


    public List<Seance> getAllSeancesForModule(Long moduleId) {
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if (optionalModule.isPresent()){
            return seanceRepository.findByModuleId(moduleId);
        }
        else {
            throw new ModuleNotFoundException("Module by id " + moduleId + " was not found" );
        }
    }

   // when a séance is added the associative table presence is automatically filled with participants and the specific session
    public Seance addSeance(Long moduleId, Seance seance) {
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if (optionalModule.isPresent()) {
            Module module = optionalModule.get();
            seance.setModule(module);
            Session session = module.getSession();
            List<Paiement> paiements = paiementRepository.findBySessionId(session.getId());
            List<Presence> presences = new ArrayList<>();
            //parcourir toutes les inscriptions de la session
            for (Paiement paiement : paiements) {

            Presence presence = new Presence();

            // participant
            Participant participant = paiement.getParticipant();
            presence.setParticipant(participant);
            participant.getPresences().add(presence);

            presence.setSeance(seance);
            presence.setPresent(false);
            presences.add(presence);
            }
            seance.setPresences(presences);
            return seanceRepository.save(seance);
        } else {
            throw new ModuleNotFoundException("Module by id " + moduleId + " was not found" );
        }
    }


    public Seance updateSeance(Seance seance) {
        Long seanceId = seance.getId();
        Optional<Seance> optionalSeance = seanceRepository.findById(seanceId);
        if (optionalSeance.isPresent()){
            Seance oldSeance = optionalSeance.get();
            seance.setModule(oldSeance.getModule());
            seance.setPresences(oldSeance.getPresences());
            return seanceRepository.save(seance);
        }
        else{
            throw new SeanceNotFoundException("seance with id "+ seanceId + " is not found");
        }
    }


    public void deleteSeance(Long seanceId) {
        seanceRepository.deleteById(seanceId);
    }
}
