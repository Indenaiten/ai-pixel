package com.aipixel.api.component.category.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;



/**
 * Value Object de tipo {@link Long} que representa el valor de un identificador de categoría.
 *
 * @see ValueObject
 */
public class CategoryId extends ValueObject<Long> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor del identificador de la categoría. */
    private final Long value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor del identificador de la categoría.
     *
     * @param value El valor del identificador de la categoría.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la categoría no es válido.
     */
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

    /**
     * Crea una nueva instancia de un identificador de categoría con el valor proporcionado.
     *
     * @param id El valor del identificador de la categoría.
     *
     * @return Una nueva instancia de un identificador de categoría con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la categoría no es válido.
     */
    public static CategoryId of( final Long id ){
        return new CategoryId( id );
    }


    /**
     * Crea una nueva instancia de un identificador de categoría con el valor proporcionado.
     *
     * @param id El valor del identificador de la categoría.
     *
     * @return Una nueva instancia de un identificador de categoría con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la categoría no es válido.
     */
    public static CategoryId of( final String id ){
        return new CategoryId( Long.valueOf( id ));
    }


    /**
     * Valida el valor del identificador de categoría proporcionado.
     *
     * @param id El valor del identificador de categoría a validar.
     *
     * @return {@code true} si el valor del identificador de categoría es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final Long id ){
        return helperValidate( () -> CategoryId.of( id ));
    }


    /**
     * Valida el valor del identificador de categoría proporcionado.
     *
     * @param id El valor del identificador de categoría a validar.
     *
     * @return {@code true} si el valor del identificador de categoría es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String id ){
        return helperValidate( () -> CategoryId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
