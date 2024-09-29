package com.aipixel.api.component.image.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;



/**
 * Enumeración que representa los tipos de contenido de imagen admitidos por la aplicación.
 */
@Getter
@AllArgsConstructor
public enum ValidContentType {

    JPG("image/jpeg", "jpg"),
    JPEG("image/jpeg", "jpeg"),
    PNG("image/png", "png"),
    GIF("image/gif", "gif"),
    WEBP("image/webp", "webp"),
    SVG("image/svg+xml", "svg");

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String contentType;
    private final String extension;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ValidContentType of( final String contentType ) {
        for( final ValidContentType type : ValidContentType.values() ) {
            if( type.getContentType().equals( contentType ) ) {
                return type;
            }
        }

        throw new IllegalArgumentException( String.format( "No existe un tipo de contenido correspondiente al valor \"%s\"", contentType ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
