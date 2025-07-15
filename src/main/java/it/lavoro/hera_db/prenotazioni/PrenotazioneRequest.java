package it.lavoro.hera_db.prenotazioni;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {
    @NotNull(message = "Il campo data non può essere vuoto")
    private Date dataMatrimonio;
    @Min( value = 1, message = "Il numero di invitati deve essere almeno 1")
    private int numeroInvitati;
    @Min( value = 1, message = "Il budget deve essere superiore a 1€")  //da settare
    private double budget;
}