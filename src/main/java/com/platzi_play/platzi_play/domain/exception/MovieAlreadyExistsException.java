package com.platzi_play.platzi_play.domain.exception;

public class MovieAlreadyExistsException extends  RuntimeException {
    public MovieAlreadyExistsException(String movieTitle){
        super("La pelicula " + movieTitle + " ya existe");
    }
}
