package it.lavoro.hera_db.prenotazioni;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.locations.Location;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazione")
@RequiredArgsConstructor
@Validated
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    @GetMapping("/location")
    public List<Prenotazione> getPrenotazioniByLocation(@AuthenticationPrincipal Location location){
        return prenotazioneService.getPrenotazioniByLocation(location);
    }

    @GetMapping("/utente")
    public Prenotazione getPrenotazioneByUtente(@AuthenticationPrincipal AppUser utente){
        return prenotazioneService.getPrenotazioneByUtente(utente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Valid PrenotazioneRequest prenotazioneRequest, @AuthenticationPrincipal AppUser utente) {
        return prenotazioneService.create(prenotazioneRequest, utente);
    }

    @PutMapping("/{id}")
    public Prenotazione update(@PathVariable Long id, @RequestBody @Valid PrenotazioneRequest prenotazioneRequest, @AuthenticationPrincipal Location location) {
        return prenotazioneService.update(id, prenotazioneRequest, location);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @AuthenticationPrincipal Location location) {
        try {
            prenotazioneService.delete(id, location);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
    }
}