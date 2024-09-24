package com.aipixel.api.common.controller.advice;

import com.aipixel.api.common.controller.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler( Exception.class )
    public ResponseEntity<ApiResponse<Void>> exception( final Exception e ) {
        e.printStackTrace();
        final ApiResponse<Void> response = ApiResponse.error().message( "Error !" ).build();
        return ResponseEntity.internalServerError().body( response );
    }

}
