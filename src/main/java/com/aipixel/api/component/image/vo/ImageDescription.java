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

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida las descripciones de imagen. */
    public static final Pattern PATTERN_IMAGE_DESCRIPTION = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9\"'¡¿(/\\\\#@*][a-záéíóúàèìòùäëïöü\\s0-9\"'!¡|?¿_+;:()/\\\\#@$€%&=*,.-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener una descripción de imagen. */
    public static final int MIN_SIZE = 5;

    /** El tamaño máximo que puede tener una descripción de imagen. */
    public static final int MAX_SIZE = 500;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor de la descripción de la imagen. */
    private final String value;


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor de la descripción de la imagen.
     *
     * @param value El valor de la descripción de la imagen.
     *
     * @throws IllegalArgumentException Si el valor de la descripción de la imagen no es válido.
     */
    public ImageDescription( final String value ){
        if( value == null || value.isEmpty() ){
            throw new IllegalArgumentException( "The Image description must not be null or empty" );
        }
        if( value.length() < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image description must have at least %d characters", MIN_SIZE ));
        }
        if( value.length() > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image description must have at most %d characters", MAX_SIZE ));
        }
        if( !PATTERN_IMAGE_DESCRIPTION.matcher( value ).matches() ){
            throw new IllegalArgumentException(
                    String.format( "The Image description must have the following format: %s", PATTERN_IMAGE_DESCRIPTION ));
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

    /**
     * Crea una nueva instancia de una descripción de imagen con el valor proporcionado.
     *
     * @param description El valor de la descripción de la imagen.
     *
     * @return Una nueva instancia de una descripción de imagen con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor de la descripción de la imagen no es válido.
     */
    public static ImageDescription of( final String description ){
        return new ImageDescription( description );
    }


    /**
     * Valida si el valor de la descripción de la imagen es válido.
     *
     * @param description El valor de la descripción de la imagen.
     *
     * @return {@code true} si el valor de la descripción de la imagen es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String description ){
        return helperValidate(() -> ImageDescription.of( description ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
