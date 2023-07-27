package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.UserNotFoundException;
import com.prestacode.systgestionformation.model.Participant;
import com.prestacode.systgestionformation.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAllParticipants(){
        return participantRepository.findAll();
    }

    public Participant findParticipantById(Long id){
        return participantRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User by id " + id + " was not found" ));
    }

    public Participant addParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Participant participant){

        Long participantId = participant.getId();
        Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
        if (optionalParticipant.isPresent()){
            Participant oldParticipant = optionalParticipant.get();
            participant.setPaiements(oldParticipant.getPaiements());
            participant.setPresences(oldParticipant.getPresences());
            return participantRepository.save(participant);
        }
        else{
            throw new UserNotFoundException("user with id "+ participantId + " is not found");
        }
    }

    public void deleteParticipant(Long id){
        participantRepository.deleteById(id);
    }


}
