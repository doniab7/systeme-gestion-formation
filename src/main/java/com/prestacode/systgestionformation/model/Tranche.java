package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tranche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float montant;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "paiement_id")
    @JsonBackReference(value = "paiement-tranches")
    private Paiement paiement;

    public Tranche(float montant, LocalDate date) {
        this.montant = montant;
        this.date = date;
    }

    public Tranche(float montant, LocalDate date, Paiement paiement) {
        this.montant = montant;
        this.date = date;
        this.paiement = paiement;
    }

}
