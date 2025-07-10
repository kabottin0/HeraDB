package it.lavoro.hera_db.auth.app_user;
import it.lavoro.hera_db.auth.authorization.RegisterRequest;
import it.lavoro.hera_db.auth.jwt.JwtTokenUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AppUser registerDefault(String username, String password, Set<Role> roles) {
        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(roles);
        return appUserRepository.save(appUser);
    }

    public AppUser registerUser(String username, String password, String nome, String cognome , String telefono, String email, Date dataMatrimonio, Set<Role> roles) {
        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setNome(nome);
        appUser.setCognome(cognome);
        appUser.setTelefono(telefono);
        appUser.setEmail(email);
        appUser.setDataMatrimonio(dataMatrimonio);
        appUser.setRoles(roles);
        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    public String authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }

    public RegisterRequest update (String username, RegisterRequest updatedUser) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        appUser.setUsername(updatedUser.getUsername());
        appUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        appUser.setNome(updatedUser.getNome());
        appUser.setCognome(updatedUser.getCognome());
        appUser.setTelefono(updatedUser.getTelefono());
        appUser.setEmail(updatedUser.getEmail());
        appUser.setDataMatrimonio(updatedUser.getDataMatrimonio());

        appUserRepository.save(appUser);

        return updatedUser;
    }

    public void delete(String username) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        appUserRepository.delete(appUser);
    }
}
