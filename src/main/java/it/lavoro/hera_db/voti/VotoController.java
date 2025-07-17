package it.lavoro.hera_db.voti;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.locations.Location;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/voto")
@RequiredArgsConstructor
public class VotoController {
    private final VotoService votoService;

    @GetMapping
    public List<Voto> getVotoByLocation(@AuthenticationPrincipal Location location) {
        return votoService.getVotoByLocation(location);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Voto create(@RequestBody VotoRequest votoRequest, @AuthenticationPrincipal AppUser utente) {
        return votoService.create(votoRequest, utente);
    }


}