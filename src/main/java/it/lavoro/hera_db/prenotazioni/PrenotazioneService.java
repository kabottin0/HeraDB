package it.lavoro.hera_db.prenotazioni;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.locations.Location;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> getPrenotazioniByLocation(Location location) {
        return prenotazioneRepository.findAllByLocation(location);
    }

    public Prenotazione getPrenotazioneByUtente(AppUser utente) {
        return prenotazioneRepository.findByUtente(utente);
    }

    public Prenotazione create(@Valid PrenotazioneRequest prenotazioneRequest, AppUser utente) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataMatrimonio( prenotazioneRequest.getDataMatrimonio());
        prenotazione.setNumeroInvitati(prenotazioneRequest.getNumeroInvitati());
        prenotazione.setBudget(prenotazioneRequest.getBudget());
        prenotazione.setUtente(utente);
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione update(Long id, PrenotazioneRequest prenotazioneRequest, Location location) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        if (prenotazione != null) {
            prenotazione.setDataMatrimonio(prenotazioneRequest.getDataMatrimonio());
            prenotazione.setNumeroInvitati(prenotazioneRequest.getNumeroInvitati());
            prenotazione.setBudget(prenotazioneRequest.getBudget());
            prenotazione.setLocation(location);
            return prenotazioneRepository.save(prenotazione);
        } else {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
    }

    public void delete(Long id,Location location) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        if (prenotazione != null) {
            if (prenotazione.getLocation().equals(location)) {
                prenotazioneRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Prenotazione non trovata");
            }
        } else {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
    }
}