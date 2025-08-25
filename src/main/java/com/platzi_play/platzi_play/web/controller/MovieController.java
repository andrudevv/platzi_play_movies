package com.platzi_play.platzi_play.web.controller;

import com.platzi_play.platzi_play.domain.dto.MovieDto;
import com.platzi_play.platzi_play.domain.dto.SuggestRequestDto;
import com.platzi_play.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi_play.platzi_play.domain.service.MovieService;
import com.platzi_play.platzi_play.domain.service.PlatziPlayAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

   private final MovieService movieService;
   private final PlatziPlayAiService aiService;

    public MovieController(MovieService movieService, PlatziPlayAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll(){
        return ResponseEntity.ok(this.movieService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener  una pelivula por su identificador",
            description = "Retorna la pelicula que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pelicula encontrada"),
                    @ApiResponse(responseCode = "404", description = "Pelicula no encontrada", content = @Content)
            }
    )
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Identificador de la pelicula", example = "9") @PathVariable Long id){
        MovieDto movieDto = this.movieService.getById(id);
        if(movieDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);

    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto){
        return ResponseEntity.ok(this.aiService.generateMovieSuggestion(suggestRequestDto.userPreferences()));
    }


    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto){

        //MovieDto movieDtoResponse = this.movieService.add(movieDto);
        //return ResponseEntity.status(HttpStatus.CREATED).body(movieDtoResponse);
        // esto para personalizar la respuesta en variable y poder crear condicionales si es requerido

        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody @Valid UpdateMovieDto updateMovieDto){
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        this.movieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
