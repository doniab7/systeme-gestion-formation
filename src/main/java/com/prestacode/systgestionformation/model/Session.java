package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String intitule;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "session-modules")
    private List<Module> modules;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "session-paiements")
    private List<Paiement> paiements;

    public Session(String intitule, LocalDate dateDebut, LocalDate dateFin, String description) {
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }

    public Session(String intitule, LocalDate dateDebut, LocalDate dateFin, String description, List<Module> modules, List<Paiement> paiements) {
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.modules = modules;
        this.paiements = paiements;
    }

}
