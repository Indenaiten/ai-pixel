package com.aipixel.api.component.tag.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;



/**
 * Value Object de tipo {@link Long} que representa el valor de un identificador de etiqueta.
 *
 * @see ValueObject
 */
public class TagId extends ValueObject<Long> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor del identificador de la etiqueta. */
    private final Long value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor del identificador de la etiqueta.
     *
     * @param value El valor del identificador de la etiqueta.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la etiqueta no es válido.
     */
    public TagId( final Long value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Tag ID must not be null" );
        }
        if( value <= 0 ){
            throw new IllegalArgumentException( "The Tag ID must be greater than 0" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public TagId copy() {
        return new TagId( this.value );
    }

    @Override
    public Long value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de un identificador de etiqueta con el valor proporcionado.
     *
     * @param id El valor del identificador de la etiqueta.
     *
     * @return Una nueva instancia de un identificador de etiqueta con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la etiqueta no es válido.
     */
    public static TagId of( final Long id ){
        return new TagId( id );
    }


    /**
     * Crea una nueva instancia de un identificador de etiqueta con el valor proporcionado.
     *
     * @param id El valor del identificador de la etiqueta.
     *
     * @return Una nueva instancia de un identificador de etiqueta con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la etiqueta no es válido.
     */
    public static TagId of( final String id ){
        return new TagId( Long.valueOf( id ));
    }


    /**
     * Valida el valor del identificador de la etiqueta.
     *
     * @param id El valor del identificador de la etiqueta.
     *
     * @return {@code true} si el valor del identificador de la etiqueta es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final Long id ){
        return helperValidate( () -> TagId.of( id ));
    }


    /**
     * Valida el valor del identificador de la etiqueta.
     *
     * @param id El valor del identificador de la etiqueta.
     *
     * @return {@code true} si el valor del identificador de la etiqueta es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String id ){
        return helperValidate( () -> TagId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
