package com.prestacode.systgestionformation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String prenom;
    private long tel;
    private String email;
    private String disponibilite;
    private String remarques;

    public Utilisateur(String nom, String prenom, long tel, String email, String disponibilite, String remarques) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.disponibilite = disponibilite;
        this.remarques = remarques;
    }

}
