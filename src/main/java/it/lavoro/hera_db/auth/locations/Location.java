package it.lavoro.hera_db.auth.locations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.lavoro.hera_db.auth.app_user.Role;
import it.lavoro.hera_db.voti.Voto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@Data
public class Location implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private String password;

    private String nome;
    private String pIva;
    private String telefono;
    @Column(unique = true)
    private String email;
    private String indirizzo;
    @OneToMany
    private List<Voto> voti = new ArrayList<>();
    private String immagine;
    @Column(unique = true)
    private String codiceUnivoco;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private  boolean accountNonExpired=true;
    private  boolean accountNonLocked=true;
    private  boolean credentialsNonExpired=true;
    private  boolean enabled=true;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    public Location (String username, String password, String nome, String telefono, String email, String indirizzo, String immagine, String codiceUnivoco, Collection<? extends GrantedAuthority> authorities) {
     this ( username, password, nome, telefono, email, indirizzo, immagine, codiceUnivoco, true, true, true, true, authorities);
    }

    public Location (
            String username,
            String password,
            String nome,
            String telefono,
            String email,
            String indirizzo,
            String immagine,
            String codiceUnivoco,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.telefono = telefono;
        this.email = email;
        this.indirizzo = indirizzo;
        this.immagine = immagine;
        this.codiceUnivoco = codiceUnivoco;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

}