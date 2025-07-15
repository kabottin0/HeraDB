package it.lavoro.hera_db.voti;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.locations.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voti")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private AppUser utente;
    @ManyToOne
    private Location location;
    private int voto;
}
