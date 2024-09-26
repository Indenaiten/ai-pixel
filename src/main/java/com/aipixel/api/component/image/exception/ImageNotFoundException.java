package com.aipixel.api.component.image.exception;



/**
 * Excepción que se lanza cuando se intenta acceder a una imagen que no existe.
 */
public class ImageNotFoundException extends Exception {

    public ImageNotFoundException( final String message ) {
        super( message );
    }

}
