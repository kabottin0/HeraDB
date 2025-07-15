package it.lavoro.hera_db.voti;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotoService {
    private final VotoRepository votoRepository;

    public Voto create(VotoRequest votoRequest) {
        Voto voto = new Voto();
        voto.setVoto(votoRequest.getVoto());
        return votoRepository.save(voto);
    }



}