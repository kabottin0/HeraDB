package it.lavoro.hera_db.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PiattoRequest {
    @NotBlank ( message = "Il nome del piatto non può essere vuoto" )
    private String nomePiatto;
    @NotNull ( message = "Il prezzo del piatto non può essere vuoto" )
    private double prezzo;
}
