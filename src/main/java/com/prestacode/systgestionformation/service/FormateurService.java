package com.prestacode.systgestionformation.service;

import com.prestacode.systgestionformation.exception.UserNotFoundException;
import com.prestacode.systgestionformation.model.Formateur;
import com.prestacode.systgestionformation.repository.FormateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FormateurService {


    private final FormateurRepository formateurRepository;

    @Autowired
    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }


    public List<Formateur> findAllFormateurs() {
        return formateurRepository.findAll();
    }

    public Formateur findFormateurById(Long id) {
        return formateurRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User by id " + id + " was not found" ));
    }


    public Formateur addFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public Formateur updateFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public void deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
    }
}
