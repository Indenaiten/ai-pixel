package com.aipixel.api.component.tag.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.regex.Pattern;



/**
 * Value Object de tipo {@link String} que representa el valor de un nombre de etiqueta.
 *
 * @see ValueObject
 */
public class TagName extends ValueObject<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    /** La expresión regular que valida los nombres de las etiquetas. */
    public static final Pattern PATTERN_TAG_NAME = Pattern.compile( "[a-záéíóúàèìòùäëïöü0-9_-][a-záéíóúàèìòùäëïöü\\s0-9_-]+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );

    /** El tamaño mínimo que puede tener un nombre de etiqueta. */
    public static final int MIN_SIZE = 3;

    /** El tamaño máximo que puede tener un nombre de etiqueta. */
    public static final int MAX_SIZE = 20;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public TagName( final String value ){
        if( value == null || value.isEmpty() ){
            throw new IllegalArgumentException( "El nombre de la etiqueta no puede ser nulo o vacío" );
        }
        if( value.length() < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "El nombre de la etiqueta debe tener al menos %d caracteres", MIN_SIZE ));
        }
        if( value.length() > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "El nombre de la etiqueta no puede tener más de %d caracteres", MAX_SIZE ));
        }
        if( !PATTERN_TAG_NAME.matcher( value ).matches() ){
            throw new IllegalArgumentException(
                    String.format( "El nombre de la etiqueta no cumple con el formato requerido: %s", PATTERN_TAG_NAME ));
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public TagName copy () {
        return new TagName( this.value );
    }

    @Override
    public String value () {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static TagName of( final String name ){
        return new TagName( name );
    }

    public static boolean validate( final String name ){
        return helperValidate(() -> TagName.of( name ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
