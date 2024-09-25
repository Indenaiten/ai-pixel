package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;



/**
 * Value Object de tipo {@link String} que representa el valor de un nombre de categoría.
 *
 * @See ValueObject
 */
public class CategoryDescription extends ValueObject<String> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida las descripciones de las categorías. */
    public static final Pattern PATTERN_IMAGE_DESCRIPTION = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9\"'¡¿(/\\\\#@*][a-záéíóúàèìòùäëïöü\\s0-9\"'!¡|?¿_+;:()/\\\\#@$€%&=*,.-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener una descripción de categoría. */
    public static final int MIN_SIZE = 5;

    /** El tamaño máximo que puede tener una descripción de categoría. */
    public static final int MAX_SIZE = 500;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor de la descripción de la categoría. */
    private final String value;


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor de la descripción de la categoría.
     *
     * @param value El valor de la descripción de la categoría.
     *
     * @throws IllegalArgumentException Si el valor de la descripción de la categoría no es válido.
     */
    public CategoryDescription(final String value ){
        if( value == null || value.isEmpty() ){
            throw new IllegalArgumentException( "The Category description must not be null or empty" );
        }
        if( value.length() < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Category description must have at least %d characters", MIN_SIZE ));
        }
        if( value.length() > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Category description must have at most %d characters", MAX_SIZE ));
        }
        if( !PATTERN_IMAGE_DESCRIPTION.matcher( value ).matches() ){
            throw new IllegalArgumentException(
                    String.format( "The Category description must have the following format: %s", PATTERN_IMAGE_DESCRIPTION ));
        }

        this.value = value;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public CategoryDescription copy () {
        return new CategoryDescription( this.value );
    }

    @Override
    public String value () {
        return this.value;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de una descripción de categoría con el valor proporcionado.
     *
     * @param description El valor de la descripción de la categoría.
     *
     * @return Una nueva instancia de una descripción de categoría con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor de la descripción de la categoría no es válido.
     */
    public static CategoryDescription of( final String description ){
        return new CategoryDescription( description );
    }


    /**
     * Valida si el valor de la descripción de la categoría es válido.
     *
     * @param description El valor de la descripción de la categoría.
     *
     * @return {@code true} si el valor de la descripción de la categoría es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String description ){
        return helperValidate(() -> CategoryDescription.of( description ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
