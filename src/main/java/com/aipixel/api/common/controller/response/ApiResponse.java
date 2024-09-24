package com.aipixel.api.common.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;



/**
 * Clase que representa una respuesta de la API REST de la aplicación.
 *
 * @param <T> Tipo de dato que se espera en la información de la respuesta.
 */
@Data
public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final ApiResponseStatusCode code;
    private final String message;
    private final T data;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene el valor del código de estado de la respuesta.
     *
     * @return El valor del código de estado de la respuesta.
     */
    @JsonProperty( "code" )
    public int getCodeValue() {
        return this.code.value();
    }


    public Optional<String> getMessage() {
        return Optional.ofNullable( this.message );
    }


    public Optional<T> getData() {
        return Optional.ofNullable( this.data );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ApiResponseBuilder builder( final ApiResponseStatusCode code ){
        return new ApiResponseBuilder( code );
    }


    public static ApiResponseBuilder builder( final int code ){
        return new ApiResponseBuilder( code );
    }


    /**
     * Crea una instancia de respuesta con el estado de éxito.
     *
     * @return Una nueva insntancia de respuesta con el estado de éxito.
     */
    public static ApiResponseBuilder success(){
        return builder( ApiResponseStatusCode.SUCCESS );
    }


    /**
     * Crea una instancia de respuesta con el estado de error genérico.
     *
     * @return Una nueva insntancia de respuesta con el estado de error genérico.
     */
    public static ApiResponseBuilder error(){
        return builder( ApiResponseStatusCode.ERROR );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
