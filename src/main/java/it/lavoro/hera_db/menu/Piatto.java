package it.lavoro.hera_db.menu;

import it.lavoro.hera_db.auth.locations.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class Piatto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomePiatto;
    @Enumerated(EnumType.STRING)
    private Categoria categoriaPiatto;
    private double prezzo;
    @OneToOne
    private Location Location;
}
