package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.UUID;



/**
 * Value Object de tipo {@link UUID} que representa el valor de un identificador de imagen.
 *
 * @see ValueObject
 */
public class ImageId extends ValueObject<UUID> {

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
