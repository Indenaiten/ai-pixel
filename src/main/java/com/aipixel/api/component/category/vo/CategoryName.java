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

    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida los nombres de las categorías. */
    public static final Pattern PATTERN_CATEGORY_NAME = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9_-][a-záéíóúàèìòùäëïöü\\s0-9_-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener un nombre de categoría. */
    public static final int MIN_SIZE = 3;

    /** El tamaño máximo que puede tener un nombre de categoría. */
    public static final int MAX_SIZE = 25;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

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

    public static CategoryName of( final String name ){
        return new CategoryName( name );
    }

    public static boolean validate( final String name ){
        return helperValidate(() -> CategoryName.of( name ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
