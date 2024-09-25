package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.Serial;



/**
 * Value Object de tipo {@link Short} que representa el valor de una valoración de imagen.
 *
 * @see ValueObject
 */
public class ImageValoration extends ValueObject<Short> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** El tamaño mínimo de la valoración de la imagen. */
    public static final int MIN_SIZE = 1;

    /** El tamaño máximo de la valoración de la imagen. */
    public static final int MAX_SIZE = 5;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor de la valoración de la imagen. */
    private final Short value;


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor de la valoración de la imagen.
     *
     * @param value El valor de la valoración de la imagen.
     *
     * @throws IllegalArgumentException Si el valor de la valoración de la imagen no es válido.
     */
    public ImageValoration(final Short value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Image valoration must not be null" );
        }
        if( value < MIN_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image valoration must have at least %d characters", MIN_SIZE ));
        }
        if( value > MAX_SIZE ){
            throw new IllegalArgumentException(
                    String.format( "The Image valoration must have at most %d characters", MAX_SIZE ));
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ValueObject<Short> copy() {
        return new ImageValoration( this.value );
    }

    @Override
    public Short value() {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Devuelve una nueva instancia de ImageValoration a partir de un valor de tipo {@link Short}.
     *
     * @param value El valor de la valoración de la imagen.
     *
     * @return Una nueva instancia de ImageValoration.
     *
     * @throws IllegalArgumentException Si el valor de la valoración de la imagen no es válido.
     */
    public static ImageValoration of( final Short value ){
        return new ImageValoration( value );
    }


    /**
     * Devuelve una nueva instancia de ImageValoration a partir de un valor de tipo {@link String}.
     *
     * @param value El valor de la valoración de la imagen.
     *
     * @return Una nueva instancia de ImageValoration.
     *
     * @throws IllegalArgumentException Si el valor de la valoración de la imagen no es válido.
     */
    public static ImageValoration of( final String value ){
        return new ImageValoration( Short.parseShort( value ));
    }


    /**
     * Valida si el valor de la valoración de la imagen es válido.
     *
     * @param value El valor de la valoración de la imagen.
     *
     * @return true si el valor de la valoración de la imagen es válido, false en caso contrario.
     */
    public static boolean validate( final Short value ){
        return helperValidate(() -> ImageValoration.of( value ));
    }


    /**
     * Valida si el valor de la valoración de la imagen es válido.
     *
     * @param value El valor de la valoración de la imagen.
     *
     * @return true si el valor de la valoración de la imagen es válido, false en caso contrario.
     */
    public static boolean validate( final String value ){
        return helperValidate(() -> ImageValoration.of( value ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
