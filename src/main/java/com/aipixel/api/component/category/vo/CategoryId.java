package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;

public class CategoryId extends ValueObject<Long> {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final Long value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public CategoryId(final Long value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Category ID must not be null" );
        }
        if( value <= 0 ){
            throw new IllegalArgumentException( "The Category ID must be greater than 0" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public CategoryId copy() {
        return new CategoryId( this.value );
    }

    @Override
    public Long value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static CategoryId of( final Long id ){
        return new CategoryId( id );
    }

    public static CategoryId of( final String id ){
        return new CategoryId( Long.valueOf( id ));
    }

    public static boolean validate( final Long id ){
        return helperValidate( () -> CategoryId.of( id ));
    }

    public static boolean validate( final String id ){
        return helperValidate( () -> CategoryId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
