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
@ToString(callSuper = true)
public class Participant extends Utilisateur {

    private boolean attestation;
    private boolean etat;
    @Enumerated(EnumType.STRING)
    private EtatPaiement etatPaiement;

    private float montant_total;

    private int nbr_heures_presence;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Paiement> paiements;

    public Participant(String nom, String prenom, long tel, String email, String disponibilite, String remarques, boolean attestation, boolean etat, EtatPaiement etatPaiement, float montant_total, int nbr_heures_presence) {
        super(nom, prenom, tel, email, disponibilite, remarques);
        this.attestation = attestation;
        this.etat = etat;
        this.etatPaiement = etatPaiement;
        this.montant_total = montant_total;
        this.nbr_heures_presence = nbr_heures_presence;
    }


}
