package it.lavoro.hera_db.auth.locations;

import it.lavoro.hera_db.auth.app_user.Role;
import it.lavoro.hera_db.auth.jwt.JwtTokenUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Location registerLocation(String username, String password, String nome,String pIva, String telefono, String email, String indirizzo,String codiceUnivoco, Set<Role> roles) {
        if (locationRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        Location location = new Location();

        location.setUsername(username);
        location.setPassword(passwordEncoder.encode(password));
        location.setNome(nome);
        location.setPIva(pIva);
        location.setTelefono(telefono);
        location.setEmail(email);
        location.setIndirizzo(indirizzo);
        location.setCodiceUnivoco(codiceUnivoco);
        location.setRoles(roles);
        return locationRepository.save(location);
    }

    public Optional<Location> findByUsername(String username) {
        return locationRepository.findByUsername(username);
    }

    public List<Location> findAllUsers() {
        return locationRepository.findAll();
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

    public RegisterLocationRequest update (String username, RegisterLocationRequest updatedUser) {
        Location location = locationRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        location.setUsername(updatedUser.getUsername());
        location.setPassword( passwordEncoder.encode(updatedUser.getPassword()) );
        location.setNome(updatedUser.getNome());
        location.setPIva(updatedUser.getPIva());
        location.setTelefono(updatedUser.getTelefono());
        location.setEmail(updatedUser.getEmail());
        location.setIndirizzo(updatedUser.getIndirizzo());
        location.setCodiceUnivoco(updatedUser.getCodiceUnivoco());
        locationRepository.save(location);

        return updatedUser;
    }

    public void delete(String username) {
        Location location = locationRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        locationRepository.delete(location);
    }
}