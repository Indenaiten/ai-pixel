package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;

public class CategoryDescription extends ValueObject<String> {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** A pattern that matches valid Category descriptions. */
    public static final Pattern PATTERN_IMAGE_DESCRIPTION = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9\"'¡¿(/\\\\#@*][a-záéíóúàèìòùäëïöü\\s0-9\"'!¡|?¿_+;:()/\\\\#@$€%&=*,.-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );
    public static final int MIN_SIZE = 5;
    public static final int MAX_SIZE = 500;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String value;


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

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

    public static CategoryDescription of( final String description ){
        return new CategoryDescription( description );
    }

    public static boolean validate( final String description ){
        return helperValidate(() -> CategoryDescription.of( description ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
