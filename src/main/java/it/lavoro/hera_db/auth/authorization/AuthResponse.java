package it.lavoro.hera_db.auth.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private Date dataMatrimonio;
    private Set<String> roles;
}

