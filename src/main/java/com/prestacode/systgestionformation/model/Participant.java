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
    private int nbrHeuresPresence;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "participant-paiement")
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "participant-presences")
    private List<Presence> presences;

    public Participant(String nom, String prenom, long tel, String email, String disponibilite, String remarques, boolean attestation, boolean etat, int nbrHeuresPresence) {
        super(nom, prenom, tel, email, disponibilite, remarques);
        this.attestation = attestation;
        this.etat = etat;
        this.nbrHeuresPresence = nbrHeuresPresence;
    }

    public Participant(String nom, String prenom, long tel, String email, String disponibilite, String remarques, boolean attestation, boolean etat, int nbrHeuresPresence, List<Paiement> paiements, List<Presence> presences) {
        super(nom, prenom, tel, email, disponibilite, remarques);
        this.attestation = attestation;
        this.etat = etat;
        this.nbrHeuresPresence = nbrHeuresPresence;
        this.paiements = paiements;
        this.presences = presences;
    }

}
