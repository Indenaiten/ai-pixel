package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;



/**
 * Value Object de tipo {@link Long} que representa el valor de un identificador de categoría.
 *
 * @see ValueObject
 */
public class CategoryId extends ValueObject<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final Long value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public CategoryId( final Long value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Category ID must not be null" );
        }
        if( value <= 0 ){
            throw new IllegalArgumentException( "The Category ID must be greater than 0" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public CategoryId copy() {
        return new CategoryId( this.value );
    }

    @Override
    public Long value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static CategoryId of( final Long id ){
        return new CategoryId( id );
    }

    public static CategoryId of( final String id ){
        return new CategoryId( Long.valueOf( id ));
    }

    public static boolean validate( final Long id ){
        return helperValidate( () -> CategoryId.of( id ));
    }

    public static boolean validate( final String id ){
        return helperValidate( () -> CategoryId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
