package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.PresenceNotFoundException;
import com.prestacode.systgestionformation.exception.SeanceNotFoundException;
import com.prestacode.systgestionformation.exception.UserNotFoundException;
import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.model.Presence;
import com.prestacode.systgestionformation.model.Seance;
import com.prestacode.systgestionformation.repository.ParticipantRepository;
import com.prestacode.systgestionformation.repository.PresenceRepository;
import com.prestacode.systgestionformation.repository.SeanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresenceService {

    private final PresenceRepository presenceRepository;
    private final ParticipantRepository participantRepository;
    private final SeanceRepository seanceRepository;

    @Autowired
    public PresenceService(PresenceRepository presenceRepository, ParticipantRepository participantRepository, SeanceRepository seanceRepository) {
        this.presenceRepository = presenceRepository;
        this.participantRepository = participantRepository;
        this.seanceRepository = seanceRepository;
    }

    public List<Presence> getAllPresences() {
        return presenceRepository.findAll();
    }


    public Presence getPresenceById(Long presenceId) {
        return presenceRepository.findById(presenceId)
                .orElseThrow( () -> new PresenceNotFoundException("presence by id " + presenceId + " was not found" ));
    }


    public List<Presence> getAllPresencesForParticipant(Long participantId) {
        Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
        if (optionalParticipant.isPresent()){
            return presenceRepository.findByParticipantId(participantId);
        }
        else {
            throw new UserNotFoundException("User by id " + participantId + " was not found" );
        }
    }


    public List<Presence> getAllPresencesForSeance(Long seanceId) {
        Optional<Seance> optionalSeance = seanceRepository.findById(seanceId);
        if (optionalSeance.isPresent()){
            return presenceRepository.findBySeanceId(seanceId);
        }
        else {
            throw new SeanceNotFoundException("seance by id " + seanceId + " was not found" );
        }
    }


    public Presence updatePresence(Presence presence) {
        Long presenceId = presence.getId();
        Optional<Presence> optionalPresence = presenceRepository.findById(presenceId);
        if (optionalPresence.isPresent()){
            Presence oldPresence = optionalPresence.get();
            oldPresence.setPresent(presence.isPresent());
            return presenceRepository.save(oldPresence);
        }
        else{
            throw new PresenceNotFoundException("presence with id "+ presenceId + " is not found");
        }
    }


}
