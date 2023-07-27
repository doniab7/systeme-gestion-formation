package com.prestacode.systgestionformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean present;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    @JsonBackReference(value = "participant-presences")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "seance_id")
    @JsonBackReference(value = "seance-presences")
    private Seance seance;

    public Presence(boolean present) {
        this.present = present;
    }

    public Presence(boolean present, Participant participant, Seance seance) {
        this.present = present;
        this.participant = participant;
        this.seance = seance;
    }


}
