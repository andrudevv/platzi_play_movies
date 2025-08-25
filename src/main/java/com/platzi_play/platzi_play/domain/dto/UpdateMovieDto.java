package com.platzi_play.platzi_play.domain.dto;

import com.platzi_play.platzi_play.domain.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UpdateMovieDto(
        @NotBlank(message = "El titulo es obligatorio")
        String title,
        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate releaseDate,
        @Min(value = 0,message = "El rating no puede ser menor que 0")
        @Min(value = 5, message = "El rating no puede ser mayor que 5")
        Double rating
) {
}
