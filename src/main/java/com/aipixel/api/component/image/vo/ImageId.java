package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;
import java.util.UUID;



/**
 * Value Object de tipo {@link UUID} que representa el valor de un identificador de imagen.
 *
 * @see ValueObject
 */
public class ImageId extends ValueObject<UUID> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor del identificador de la imagen. */
    private final UUID value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor del identificador de la imagen.
     *
     * @param value El valor del identificador de la imagen.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la imagen no es válido.
     */
    public ImageId( final UUID value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Image ID must not be null" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ImageId copy() {
        return new ImageId( this.value );
    }

    @Override
    public UUID value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea un nuevo identificador de imagen a partir de un valor de tipo {@link String}.
     *
     * @param id El valor de tipo {@link String} del identificador de imagen.
     *
     * @return Un nuevo identificador de imagen.
     *
     * @throws IllegalArgumentException Si el valor del identificador de la imagen no es válido.
     */
    public static ImageId of( final String id ){
        return new ImageId( UUID.fromString( id ));
    }


    /**
     * Crea un nuevo identificador de imagen a partir de un valor aleatorio de tipo {@link UUID}.
     *
     * @return Un nuevo identificador de imagen.
     */
    public static ImageId random(){
        return new ImageId( UUID.randomUUID() );
    }


    /**
     * Valida si el valor proporcionado es un identificador de imagen válido.
     *
     * @param id El valor del identificador de imagen.
     *
     * @return {@code true} si el valor proporcionado es un identificador de imagen válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String id ){
        return helperValidate( () -> ImageId.of( id ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
