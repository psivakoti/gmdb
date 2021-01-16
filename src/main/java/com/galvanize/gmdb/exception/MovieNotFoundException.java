package com.galvanize.gmdb.exception;

public class MovieNotFoundException extends Throwable {

    public MovieNotFoundException(){
        super("Movie not found");
    }
}
