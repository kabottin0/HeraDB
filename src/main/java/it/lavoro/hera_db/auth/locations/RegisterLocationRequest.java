package it.lavoro.hera_db.auth.locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLocationRequest {
    private String username;
    private String password;
    private String nome;
    private String pIva;
    private String telefono;
    private String email;
    private String indirizzo;
    private String codiceUnivoco;
}