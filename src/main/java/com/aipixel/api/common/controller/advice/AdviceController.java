package com.aipixel.api.common.controller.advice;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.common.controller.response.ApiResponseStatusCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * Clase que maneja las respuestas de la API REST de la aplicación cuando se producen Excepciones.
 */
@Order( 5 )
@ControllerAdvice
public class AdviceController {


    @ExceptionHandler( IllegalArgumentException.class )
    public ResponseEntity<ApiResponse<Void>> exception( final IllegalArgumentException e ) {
        e.printStackTrace();
        final ApiResponse<Void> response = ApiResponse.builder( ApiResponseStatusCode.ERROR_VALIDATION )
                .message( e.getMessage() ).build();
        return ResponseEntity.internalServerError().body( response );
    }


    @ExceptionHandler( Exception.class )
    public ResponseEntity<ApiResponse<Void>> exception( final Exception e ) {
        e.printStackTrace();
        final ApiResponse<Void> response = ApiResponse.error().message( "Error !" ).build();
        return ResponseEntity.internalServerError().body( response );
    }

}
