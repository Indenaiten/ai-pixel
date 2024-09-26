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

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final File value;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

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

    public String getNameValue() {
        return this.value.getName();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDERS & VALIDATORS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageFileName of( final File fileName ){
        return new ImageFileName( fileName );
    }

    public static ImageFileName of( final String fileName ){
        return new ImageFileName( new File( fileName ));
    }

    public static boolean validate( final File fileName ){
        return helperValidate(() -> ImageFileName.of( fileName ));
    }

    public static boolean validate( final String fileName ){
        return helperValidate(() -> ImageFileName.of( fileName ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
