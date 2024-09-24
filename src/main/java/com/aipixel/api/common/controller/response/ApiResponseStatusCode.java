package com.aipixel.api.common.controller.response;

import lombok.AllArgsConstructor;



/**
 * Enumeración que representa los códigos de estado posibles de una respuesta de la API REST de la aplicación.
 */
@AllArgsConstructor
public enum ApiResponseStatusCode {

    /** Respuesta exitosa. */
    SUCCESS( 0 ),

    /** Error genérico. */
    ERROR( 1 );



// ------------------------------------------------------------------------------------------------------------------ \\

    /** Valor del código de estado. */
    private final int value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| METHODS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene el valor del código de estado.
     *
     * @return El valor del código de estado.
     */
    public int value(){
        return this.value;
    }


    /**
     * Obtiene el Enum correspondiente a partir de un valor de código de estado.
     *
     * @param value Valor del código de estado.
     *
     * @return El Enum correspondiente.
     *
     * @throws IllegalArgumentException Si no se encuentra un Enum correspondiente al valor especificado.
     */
    public static ApiResponseStatusCode of( final int value ) {
        for ( final ApiResponseStatusCode code : ApiResponseStatusCode.values() ) {
            if (code.value() == value) {
                return code;
            }
        }
        throw new IllegalArgumentException( String.format( "No enum constant with value %d", value ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
