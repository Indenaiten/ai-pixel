package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;



/**
 * Value Object de tipo {@link String} que representa el valor de un nombre de categoría.
 *
 * @See ValueObject
 */
public class CategoryName extends ValueObject<String> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida los nombres de las categorías. */
    public static final Pattern PATTERN_CATEGORY_NAME = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9_-][a-záéíóúàèìòùäëïöü\\s0-9_-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener un nombre de categoría. */
    public static final int MIN_SIZE = 3;

    /** El tamaño máximo que puede tener un nombre de categoría. */
    public static final int MAX_SIZE = 25;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor del nombre de la categoría. */
    private final String value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor del nombre de la categoría.
     *
     * @param value El valor del nombre de la categoría.
     *
     * @throws IllegalArgumentException Si el valor del nombre de la categoría no es válido.
     */
    public CategoryName( final String value ){
        if( value == null || value.isEmpty() ){
            throw new IllegalArgumentException( "The Category name must not be null or empty" );
        }
        if( value.length() < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Category name must have at least %d characters", MIN_SIZE ));
        }
        if( value.length() > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The v name must have at most %d characters", MAX_SIZE ));
        }
        if( !PATTERN_CATEGORY_NAME.matcher( value ).matches() ){
            throw new IllegalArgumentException(
                    String.format( "The Category name must have the following format: %s", PATTERN_CATEGORY_NAME));
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public CategoryName copy () {
        return new CategoryName( this.value );
    }

    @Override
    public String value () {
        return this.value;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de CategoryName con el valor proporcionado como nombre de la categoría.
     *
     * @param name El valor del nombre de la categoría.
     *
     * @return Una nueva instancia de CategoryName con el valor proporcionado como nombre de la categoría.
     *
     * @throws IllegalArgumentException Si el valor del nombre de la categoría no es válido.
     */
    public static CategoryName of( final String name ){
        return new CategoryName( name );
    }


    /**
     * Valida el valor del nombre de la categoría proporcionado.
     *
     * @param name El valor del nombre de la categoría a validar.
     *
     * @return {@code true} si el valor del nombre de la categoría es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String name ){
        return helperValidate(() -> CategoryName.of( name ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
