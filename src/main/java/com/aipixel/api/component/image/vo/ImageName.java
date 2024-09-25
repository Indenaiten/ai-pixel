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

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida los nombres de las imágenes. */
    public static final Pattern PATTERN_IMAGE_NAME = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9\"'¡¿(/\\\\#@*][a-záéíóúàèìòùäëïöü\\s0-9\"'!¡|?¿_+;:()/\\\\#@$€%&=*,.-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener un nombre de imagen. */
    public static final int MIN_SIZE = 3;

    /** El tamaño máximo que puede tener un nombre de imagen. */
    public static final int MAX_SIZE = 50;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor del nombre de la imagen. */
    private final String value;


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor del nombre de la imagen.
     *
     * @param value El valor del nombre de la imagen.
     *
     * @throws IllegalArgumentException Si el valor del nombre de la imagen no es válido.
     */
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

    /**
     * Crea un nuevo nombre de imagen a partir de un valor de tipo {@link String}.
     *
     * @param name El valor de tipo String del nombre de imagen.
     *
     * @return Un nuevo nombre de imagen.
     *
     * @throws IllegalArgumentException Si el valor del nombre de la imagen no es válido.
     */
    public static ImageName of( final String name ){
        return new ImageName( name );
    }


    /**
     * Valida si el valor del nombre de la imagen es válido.
     *
     * @param name El valor del nombre de la imagen.
     *
     * @return {@code true} si el valor del nombre de la imagen es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String name ){
        return helperValidate(() -> ImageName.of( name ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
