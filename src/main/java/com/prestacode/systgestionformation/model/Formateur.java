package com.prestacode.systgestionformation.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Formateur extends Utilisateur {

    private float coutParH;
    private String specialite;

    public Formateur(String nom, String prenom, long tel, String email, String disponibilite, String remarques, float coutParH, String specialite) {
        super(nom, prenom, tel, email, disponibilite, remarques);
        this.coutParH = coutParH;
        this.specialite = specialite;
    }

}
