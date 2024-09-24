package com.aipixel.api.common.controller.advice;

import com.aipixel.api.common.controller.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * Clase que maneja las respuestas de la API REST de la aplicación cuando se producen Excepciones.
 */
@ControllerAdvice
public class AdviceController {

    /**
     * Maneja las excepciones generales que se producen en la aplicación.
     *
     * @param e Excepción que se ha producido.
     *
     * @return La respuesta de la API REST correspondiente.
     */
    @ExceptionHandler( Exception.class )
    public ResponseEntity<ApiResponse<Void>> exception( final Exception e ) {
        e.printStackTrace();
        final ApiResponse<Void> response = ApiResponse.error().message( "Error !" ).build();
        return ResponseEntity.internalServerError().body( response );
    }

}
