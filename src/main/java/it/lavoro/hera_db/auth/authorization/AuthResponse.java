package it.lavoro.hera_db.auth.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token; // autenticazione
    private String username;
    private String telefono;
    private String email;
    private Set<String> roles;
}

