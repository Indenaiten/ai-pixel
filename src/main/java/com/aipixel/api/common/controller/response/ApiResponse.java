package com.aipixel.api.common.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final ApiErrorCode code;
    private final String message;
    private final T data;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTER METHODS |--------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @JsonProperty( "code" )
    public int getCodeValue() {
        return code.value();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ApiResponseBuilder builder( final ApiErrorCode code ){
        return new ApiResponseBuilder( code );
    }

    public static ApiResponseBuilder builder( final int code ){
        return new ApiResponseBuilder( code );
    }

    public static ApiResponseBuilder success(){
        return builder( ApiErrorCode.SUCCESS );
    }

    public static ApiResponseBuilder error(){
        return builder( ApiErrorCode.ERROR );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
