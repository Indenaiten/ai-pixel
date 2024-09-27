package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;


/**
 * Un Value Object de tipo {@link String} que representa el valor de una descripción de imagen.
 *
 * @see ValueObject
 */
public class ImageDescription extends ValueObject<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida las descripciones de imagen. */
    public static final Pattern PATTERN_IMAGE_DESCRIPTION = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9\"'¡¿(/\\\\#@*][a-záéíóúàèìòùäëïöü\\s0-9\"'!¡|?¿_+;:()/\\\\#@$€%&=*,.-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener una descripción de imagen. */
    public static final int MIN_SIZE = 5;

    /** El tamaño máximo que puede tener una descripción de imagen. */
    public static final int MAX_SIZE = 500;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageDescription( final String value ){
        if( value == null || value.isEmpty() ){
            throw new IllegalArgumentException( "La descripción de la imagen no puede ser nula o vacía" );
        }
        if( value.length() < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "La descripción de la imagen debe tener al menos %d caracteres", MIN_SIZE ));
        }
        if( value.length() > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "La descripción de la imagen no puede tener más de %d caracteres", MAX_SIZE ));
        }
        if( !PATTERN_IMAGE_DESCRIPTION.matcher( value ).matches() ){
            throw new IllegalArgumentException(
                    String.format( "La descripción de la imagen no cumple con el formato requerido: %s", PATTERN_IMAGE_DESCRIPTION ));
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ImageDescription copy () {
        return new ImageDescription( this.value );
    }

    @Override
    public String value () {
        return this.value;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageDescription of( final String description ){
        return new ImageDescription( description );
    }

    public static boolean validate( final String description ){
        return helperValidate(() -> ImageDescription.of( description ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
