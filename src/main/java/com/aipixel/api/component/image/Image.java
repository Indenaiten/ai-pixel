package com.aipixel.api.component.image;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.enumeration.ValidContentType;
import com.aipixel.api.component.image.vo.*;
import com.aipixel.api.component.tag.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;



/**
 * Clase que representa una imagen en la aplicación.
 *
 * @see Entity
 */
@Getter
@Builder
@AllArgsConstructor
public class Image implements Entity {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final ImageId id;

    @Getter( AccessLevel.NONE )
    private Boolean favorite;

    private LocalDate date;
    private final ImageName name;
    private final ImageFileName fileName;
    private ImageDescription description;
    private ImageValoration imageValoration;
    private Set<Category> categories;
    private Set<Tag> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        final Image image = (Image) o;
        return Objects.equals( this.id, image.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<ImageDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<ImageValoration> getImageValoration() {
        return Optional.ofNullable( this.imageValoration );
    }

    public Optional<LocalDateTime> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageBuilder builder( final ImageId id, final ImageName name, final ImageFileName fileName ){
        if( id == null ) throw new IllegalArgumentException( "El identificador de la imagen no puede ser nula" );
        if( name == null ) throw new IllegalArgumentException( "El nombre de la imagen no puede ser nulo" );
        if( fileName == null ) throw new IllegalArgumentException( "El nombre del fichero de la imagen no puede ser nulo" );

        return new ImageBuilder().id( id ).name( name ).fileName( fileName );
    }

    public static ImageBuilder create( final ImageName name, final ValidContentType contentType ){
        if( name == null ) throw new IllegalArgumentException( "No se puede crear una imagen sin nombre" );
        if( contentType == null ) throw new IllegalArgumentException( "No se puede crear una imagen sin un tipo de contenido válido" );

        final ImageId id = ImageId.random();
        final String fileName = String.format( "%s.%s", id, contentType.getExtension() );

        return new ImageBuilder().id( id ).name( name ).fileName( ImageFileName.of( fileName ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
