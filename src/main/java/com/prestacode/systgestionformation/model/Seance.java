package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private float duree;
    private float heureDebut;
    private String module;
    private int salle;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @JsonBackReference
    private Session session;

    public Seance(Date date, float duree, float heureDebut, String module, int salle) {
        this.date = date;
        this.duree = duree;
        this.heureDebut = heureDebut;
        this.module = module;
        this.salle = salle;
    }

}
