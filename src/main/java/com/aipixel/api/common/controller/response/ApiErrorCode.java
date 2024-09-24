package com.aipixel.api.common.controller.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiErrorCode {

    SUCCESS( 0 ),
    ERROR( 1 );



// ------------------------------------------------------------------------------------------------------------------ \\

    private final int value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| METHODS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public int value(){
        return this.value;
    }

    public static ApiErrorCode of( final int value ) {
        for ( final ApiErrorCode code : ApiErrorCode.values() ) {
            if (code.value() == value) {
                return code;
            }
        }
        throw new IllegalArgumentException( String.format( "No enum constant with value %d", value ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
