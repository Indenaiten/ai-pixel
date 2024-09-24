package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;

public class CategoryName extends ValueObject<String> {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** A pattern that matches valid Category names. */
    public static final Pattern PATTERN_CATEGORY_NAME = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9_-][a-záéíóúàèìòùäëïöü\\s0-9_-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );
    public static final int MIN_SIZE = 3;
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
