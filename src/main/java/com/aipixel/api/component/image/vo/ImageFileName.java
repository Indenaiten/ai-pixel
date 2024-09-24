package com.aipixel.api.component.image.vo;

import com.aipixel.api.common.vo.ValueObject;

import java.io.File;
import java.io.Serial;

public class ImageFileName extends ValueObject<File> {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
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

    public String getName () {
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
