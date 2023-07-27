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
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float montantTotal;
    @Enumerated(EnumType.STRING)
    private EtatPaiement etatPaiement;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    @JsonBackReference(value = "participant-paiement")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @JsonBackReference(value = "session-paiements")
    private Session session;

    @OneToMany(mappedBy = "paiement", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "paiement-tranches")
    private List<Tranche> tranches;

    public Paiement(float montantTotal, EtatPaiement etatPaiement) {
        this.montantTotal = montantTotal;
        this.etatPaiement = etatPaiement;
    }

    public Paiement(float montantTotal, EtatPaiement etatPaiement, Participant participant, Session session, List<Tranche> tranches) {
        this.montantTotal = montantTotal;
        this.etatPaiement = etatPaiement;
        this.participant = participant;
        this.session = session;
        this.tranches = tranches;
    }

}
