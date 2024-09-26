package com.aipixel.api.common.controller.advice;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.common.controller.response.ApiResponseStatusCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Clase que maneja las respuestas de la API REST de la aplicación cuando se producen Excepciones.
 */
@Order( 5 )
@ControllerAdvice
public class AdviceController {

    @ExceptionHandler( ConstraintViolationException.class )
    public ResponseEntity<ApiResponse<Map<String, String>>> exception( final ConstraintViolationException e ) {
        final Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach( violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put( fieldName, errorMessage );
        });

        final ApiResponse<Map<String, String>> response = ApiResponse.builder( ApiResponseStatusCode.ERROR_VALIDATION )
                .build( errors );
        return ResponseEntity.badRequest().body( response );
    }


    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ApiResponse<Map<String, String>>> exception( final MethodArgumentNotValidException e ) {
        final Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach( error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put( fieldName, errorMessage );
        });

        final ApiResponse<Map<String, String>> response = ApiResponse.builder( ApiResponseStatusCode.ERROR_VALIDATION )
                .build( errors );
        return ResponseEntity.badRequest().body( response );
    }


    @ExceptionHandler( IllegalArgumentException.class )
    public ResponseEntity<ApiResponse<Void>> exception( final IllegalArgumentException e ) {
        e.printStackTrace();
        final ApiResponse<Void> response = ApiResponse.builder( ApiResponseStatusCode.ERROR_VALIDATION )
                .message( e.getMessage() ).build();
        return ResponseEntity.badRequest().body( response );
    }


    @ExceptionHandler( Exception.class )
    public ResponseEntity<ApiResponse<Void>> exception( final Exception e ) {
        e.printStackTrace();
        final ApiResponse<Void> response = ApiResponse.error().message( "Error !" ).build();
        return ResponseEntity.internalServerError().body( response );
    }

}
