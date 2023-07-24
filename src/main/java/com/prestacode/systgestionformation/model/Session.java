package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private float duree;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonBackReference
    private Formation formation;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Seance> seances;

    public Session(String nom, Date dateDebut, Date dateFin, String description, float duree) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.duree = duree;
    }

}
