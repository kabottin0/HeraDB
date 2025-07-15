package it.lavoro.hera_db.auth.locations;

import it.lavoro.hera_db.auth.app_user.Role;
import it.lavoro.hera_db.auth.authorization.AuthResponse;
import it.lavoro.hera_db.auth.authorization.LoginRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/location")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LocationController {
    private final LocationService locationService;

        @GetMapping("/current-user")
        @PreAuthorize("isAuthenticated()")
        public Location getCurrentUser(@AuthenticationPrincipal Location location) {
            return location;
        }

        @PostMapping("/register")
        public ResponseEntity<String> registerLocation(@RequestBody @Valid RegisterLocationRequest registerRequest) {
            locationService.registerLocation(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getNome(),
                    registerRequest.getPIva(),
                    registerRequest.getTelefono(),
                    registerRequest.getEmail(),
                    registerRequest.getIndirizzo(),
                    registerRequest.getCodiceUnivoco(),
                    Set.of(Role.ROLE_LOCATION) // Assegna il ruolo di default
            );
            return ResponseEntity.ok("Registrazione avvenuta con successo");
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
            String token = locationService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

            Location user = locationService.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Trasforma in stringhe i ruoli
            Set<String> roles = user.getRoles().stream()
                    .map(role -> role.name())
                    .collect(Collectors.toSet());

            return ResponseEntity.ok(new AuthResponse(
                    token,
                    user.getUsername(),
                    user.getTelefono(),
                    user.getEmail(),
                    roles
            ));
        }

        @GetMapping("/users")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> findAllUsers() {
            return ResponseEntity.ok(locationService.findAllUsers());
        }

        @PutMapping("/update")
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<RegisterLocationRequest> updateUser(@AuthenticationPrincipal Location location, @RequestBody @Valid RegisterLocationRequest updatedUser) {
            RegisterLocationRequest updatedUserResponse = locationService.update(location.getUsername(), updatedUser);
            return ResponseEntity.ok(updatedUserResponse);
        }

        @DeleteMapping("/delete")
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<String> deleteUser(@AuthenticationPrincipal Location location) {
            locationService.delete(location.getUsername());
            return ResponseEntity.ok("Utente eliminato con successo");
        }

        @DeleteMapping("/delete/{username}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
            try {
                locationService.delete(username);
                return ResponseEntity.ok("Utente eliminato con successo");
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }


}