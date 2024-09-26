package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;



/**
 * Value Object de tipo {@link Short} que representa el valor de una valoración de imagen.
 *
 * @see ValueObject
 */
public class ImageValoration extends ValueObject<Short> {

    @Serial
    private static final long serialVersionUID = 1L;

    /** El tamaño mínimo de la valoración de la imagen. */
    public static final int MIN_SIZE = 1;

    /** El tamaño máximo de la valoración de la imagen. */
    public static final int MAX_SIZE = 5;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final Short value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageValoration(final Short value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Image valoration must not be null" );
        }
        if( value < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image valoration must have at least %d characters", MIN_SIZE ));
        }
        if( value > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image valoration must have at most %d characters", MAX_SIZE ));
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ValueObject<Short> copy() {
        return new ImageValoration( this.value );
    }

    @Override
    public Short value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageValoration of( final Short value ){
        return new ImageValoration( value );
    }

    public static ImageValoration of( final String value ){
        return new ImageValoration( Short.parseShort( value ));
    }

    public static boolean validate( final Short value ){
        return helperValidate(() -> ImageValoration.of( value ));
    }

    public static boolean validate( final String value ){
        return helperValidate(() -> ImageValoration.of( value ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
