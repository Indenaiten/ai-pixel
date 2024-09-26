package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;



/**
 * Value Object de tipo {@link String} que representa el valor de un nombre de imagen.
 *
 * @see ValueObject
 */
public class ImageName extends ValueObject<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida los nombres de las imágenes. */
    public static final Pattern PATTERN_IMAGE_NAME = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9\"'¡¿(/\\\\#@*][a-záéíóúàèìòùäëïöü\\s0-9\"'!¡|?¿_+;:()/\\\\#@$€%&=*,.-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener un nombre de imagen. */
    public static final int MIN_SIZE = 3;

    /** El tamaño máximo que puede tener un nombre de imagen. */
    public static final int MAX_SIZE = 50;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageName( final String value ){
        if( value == null || value.isEmpty() ){
            throw new IllegalArgumentException( "The Image name must not be null or empty" );
        }
        if( value.length() < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image name must have at least %d characters", MIN_SIZE ));
        }
        if( value.length() > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image name must have at most %d characters", MAX_SIZE ));
        }
        if( !PATTERN_IMAGE_NAME.matcher( value ).matches() ){
            throw new IllegalArgumentException(
                    String.format( "The Image name must have the following format: %s", PATTERN_IMAGE_NAME ));
        }

        this.value = value;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ImageName copy () {
        return new ImageName( this.value );
    }

    @Override
    public String value () {
        return this.value;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageName of( final String name ){
        return new ImageName( name );
    }

    public static boolean validate( final String name ){
        return helperValidate(() -> ImageName.of( name ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
