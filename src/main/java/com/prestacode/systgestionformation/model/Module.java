package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Module {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int duree;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    @JsonBackReference(value = "formateur-modules")
    private Formateur formateur;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "module-seances")
    private List<Seance> seances;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @JsonBackReference(value = "session-modules")
    private Session session;

    public Module(String nom, int duree, Formateur formateur, List<Seance> seances, Session session) {
        this.nom = nom;
        this.duree = duree;
        this.formateur = formateur;
        this.seances = seances;
        this.session = session;
    }

}
