package com.aipixel.api.common.controller.response;



/**
 * Clase que permite construir una respuesta de la API REST de la aplicación.
 */
public class ApiResponseBuilder{


    private ApiResponseStatusCode code;
    private String message;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ApiResponseBuilder( final ApiResponseStatusCode code ) {
        this.code = code;
    }


    public ApiResponseBuilder( final int code ) {
        this.code = ApiResponseStatusCode.of( code );
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| SETTER METHODS |--------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ApiResponseBuilder message( final String message ){
        this.message = message;
        return this;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Construye una respuesta con el estado, el mensaje especificados e información.
     *
     * @param <T> Tipo de la información de la respuesta.
     * @param data Información de la respuesta.
     *
     * @return Una nueva instancia de respuesta con el estado, el mensaje especificados y la información especificada.
     */
    public <T> ApiResponse<T> build( final T data ){
        return new ApiResponse<>( this.code, this.message, data );
    }


    /**
     * Construye una respuesta con el estado y el mensaje especificados, sin información.
     *
     * @return Una nueva instancia de respuesta con el estado y el mensaje especificados, sin información.
     */
    public ApiResponse<Void> build(){
        return new ApiResponse<>( this.code, this.message, null );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
