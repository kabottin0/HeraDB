package it.lavoro.hera_db.auth.authorization;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private Date dataMatrimonio;
}
