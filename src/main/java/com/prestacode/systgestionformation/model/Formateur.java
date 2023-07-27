package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Formateur extends Utilisateur {

    private float coutParH;
    private String specialite;

    @OneToMany(mappedBy = "formateur", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "formateur-modules")
    private List<Module> modules;

    public Formateur(String nom, String prenom, long tel, String email, String disponibilite, String remarques, float coutParH, String specialite, List<Module> modules) {
        super(nom, prenom, tel, email, disponibilite, remarques);
        this.coutParH = coutParH;
        this.specialite = specialite;
        this.modules = modules;
    }

    public Formateur(String nom, String prenom, long tel, String email, String disponibilite, String remarques, float coutParH, String specialite) {
        super(nom, prenom, tel, email, disponibilite, remarques);
        this.coutParH = coutParH;
        this.specialite = specialite;
    }
}
