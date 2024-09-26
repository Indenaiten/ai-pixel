package com.aipixel.api.component.image.controller;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.exception.ImageNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * Clase que maneja las respuestas de la API REST de la aplicación cuando se producen Excepciones relacionadas con las
 * imágenes.
 */
@Order( 1 )
@ControllerAdvice
public class ImageAdviceController {

    @ExceptionHandler( ImageNotFoundException.class )
    public ResponseEntity<ApiResponse<Void>> exception( final ImageNotFoundException e ) {
        final ApiResponse<Void> response = ApiResponse.error().message( e.getMessage() ).build();
        return ResponseEntity.internalServerError().body( response );
    }

}
