package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private LocalDate date;
    private int duree;
    private int salle;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonBackReference(value = "module-seances")
    private Module module;

    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "seance-presences")
    private List<Presence> presences;

    public Seance(LocalDate date, int duree, int salle) {
        this.date = date;
        this.duree = duree;
        this.salle = salle;
    }

    public Seance(LocalDate date, int duree, int salle, Module module, List<Presence> presences) {
        this.date = date;
        this.duree = duree;
        this.salle = salle;
        this.module = module;
        this.presences = presences;
    }

}
