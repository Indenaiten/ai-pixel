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

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** Estado de la respuesta. */
    private final ApiResponseStatusCode code;

    /** Mensaje de la respuesta. */
    private final String message;

    /** La información de la respuesta. */
    private final T data;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTER METHODS |--------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene el valor del código de estado de la respuesta.
     *
     * @return El valor del código de estado de la respuesta.
     */
    @JsonProperty( "code" )
    public int getCodeValue() {
        return code.value();
    }


    /**
     * Obtiene el mensaje de la respuesta si dispone de él.
     *
     * @return El mensaje de la respuesta si dispone de él.
     */
    public Optional<String> getMessage() {
        return Optional.ofNullable( this.message );
    }


    /**
     * Obtiene la información de la respuesta si dispone de ella.
     *
     * @return La información de la respuesta si dispone de ella.
     */
    public Optional<T> getData() {
        return Optional.ofNullable( this.data );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una instancia de respuesta con el estado especificado.
     *
     * @param code Estado de la respuesta.
     *
     * @return Una nueva insntancia de respuesta con el estado especificado.
     */
    public static ApiResponseBuilder builder( final ApiResponseStatusCode code ){
        return new ApiResponseBuilder( code );
    }


    /**
     * Crea una instancia de respuesta con el código de estado especificado.
     *
     * @param code Código de estado de la respuesta.
     *
     * @return Una nueva insntancia de respuesta con el estado especificado.
     */
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
     * Crea una instancia de respuesta con el estado de error.
     *
     * @return Una nueva insntancia de respuesta con el estado de error.
     */
    public static ApiResponseBuilder error(){
        return builder( ApiResponseStatusCode.ERROR );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
