package com.aipixel.api.component.tag.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;

public class TagId extends ValueObject<Long> {

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

    public TagId( final Long value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Tag ID must not be null" );
        }
        if( value <= 0 ){
            throw new IllegalArgumentException( "The Tag ID must be greater than 0" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public TagId copy() {
        return new TagId( this.value );
    }

    @Override
    public Long value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static TagId of( final Long id ){
        return new TagId( id );
    }

    public static TagId of( final String id ){
        return new TagId( Long.valueOf( id ));
    }

    public static boolean validate( final Long id ){
        return helperValidate( () -> TagId.of( id ));
    }

    public static boolean validate( final String id ){
        return helperValidate( () -> TagId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
