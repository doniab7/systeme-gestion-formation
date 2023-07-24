package com.prestacode.systgestionformation.model;

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
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float duree;

    private String description;


    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Module> modules;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Session> sessions;


    public Formation(float duree, String description) {
        this.duree = duree;
        this.description = description;
    }



}
