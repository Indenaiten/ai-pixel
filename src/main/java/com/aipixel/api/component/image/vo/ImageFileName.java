package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.File;
import java.io.Serial;



/**
 * Value Object de tipo {@link File} que representa el valor de un nombre de archivo de imagen.
 *
 * @see ValueObject
 */
public class ImageFileName extends ValueObject<File> {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El valor del nombre de archivo de la imagen. */
    private final File value;


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir del valor del nombre de archivo de la imagen.
     *
     * @param value El valor del nombre de archivo de la imagen.
     *
     * @throws IllegalArgumentException Si el valor del nombre de archivo de la imagen no es válido.
     */
    public ImageFileName( final File value ){
        if( value == null ){
            throw new IllegalArgumentException( "The Image file name must not be null" );
        }

        this.value = value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ImageFileName copy () {
        return new ImageFileName( this.value );
    }

    @Override
    public File value () {
        return this.value;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| FACADE METHODS |--------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Devuelve el nombre del archivo de la imagen.
     *
     * @return El nombre del archivo de la imagen.
     */
    public String getName () {
        return this.value.getName();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de un nombre de archivo de imagen con el valor proporcionado.
     *
     * @param fileName El valor del nombre de archivo de la imagen.
     *
     * @return Una nueva instancia de un nombre de archivo de imagen con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor del nombre de archivo de la imagen no es válido.
     */
    public static ImageFileName of( final File fileName ){
        return new ImageFileName( fileName );
    }


    /**
     * Crea una nueva instancia de un nombre de archivo de imagen con el valor proporcionado.
     *
     * @param fileName El valor del nombre de archivo de la imagen.
     *
     * @return Una nueva instancia de un nombre de archivo de imagen con el valor proporcionado.
     *
     * @throws IllegalArgumentException Si el valor del nombre de archivo de la imagen no es válido.
     */
    public static ImageFileName of( final String fileName ){
        return new ImageFileName( new File( fileName ));
    }


    /**
     * Valida el valor del nombre de archivo de la imagen.
     *
     * @param fileName El valor del nombre de archivo de la imagen.
     *
     * @return {@code true} si el valor del nombre de archivo de la imagen es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final File fileName ){
        return helperValidate(() -> ImageFileName.of( fileName ));
    }


    /**
     * Valida el valor del nombre de archivo de la imagen.
     *
     * @param fileName El valor del nombre de archivo de la imagen.
     *
     * @return {@code true} si el valor del nombre de archivo de la imagen es válido, {@code false} en caso contrario.
     */
    public static boolean validate( final String fileName ){
        return helperValidate(() -> ImageFileName.of( fileName ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
