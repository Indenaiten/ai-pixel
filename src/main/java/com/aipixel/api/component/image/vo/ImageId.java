package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.UUID;

public class ImageId extends ValueObject<UUID> {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final UUID value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageId( final UUID value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Image ID must not be null" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ImageId copy() {
        return new ImageId( this.value );
    }

    @Override
    public UUID value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageId of( final String id ){
        return new ImageId( UUID.fromString( id ));
    }

    public static ImageId random(){
        return new ImageId( UUID.randomUUID() );
    }

    public static boolean validate( final String id ){
        return helperValidate( () -> ImageId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
