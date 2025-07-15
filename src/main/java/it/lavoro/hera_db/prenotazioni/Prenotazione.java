package it.lavoro.hera_db.prenotazioni;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.locations.Location;
import it.lavoro.hera_db.menu.Piatto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private AppUser utente;
    @ManyToOne
    private Location location;
    private Date dataMatrimonio;
    private int numeroInvitati;
    private double budget;
    @ManyToOne
    private Piatto piatto;
    private Stato statoPrenotazione;
}
