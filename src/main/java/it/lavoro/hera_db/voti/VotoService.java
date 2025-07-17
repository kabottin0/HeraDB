package it.lavoro.hera_db.voti;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.locations.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VotoService {
    private final VotoRepository votoRepository;

    public List<Voto> getVotoByLocation (Location location){
        return votoRepository.findAllByLocation(location);
    }

    public Voto create(VotoRequest votoRequest, AppUser utente) {
        Voto voto = new Voto();
        voto.setVoto(votoRequest.getVoto());
        voto.setUtente(utente);
        return votoRepository.save(voto);
    }

}