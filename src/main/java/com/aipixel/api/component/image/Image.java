package com.aipixel.api.component.image;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.vo.*;
import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
public class Image implements Entity {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private final ImageId id;
    private Boolean favorite;
    private LocalDate date;
    private ImageName name;
    private ImageFileName fileName;
    private ImageDescription description;
    private ImageValoration imageValoration;
    private Set<Category> categories;
    private Set<Tag> tags;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static Image of(
            final String id,
            final Boolean favorite,
            final LocalDate date,
            final String name,
            final String fileName,
            final String description,
            final Short valoration,
            final List<Category> categories,
            final List<Tag> tags,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){
        final ImageDescription imageDescripion = description != null ? ImageDescription.of( description ) : null;
        final ImageValoration imageValoration = valoration != null ? ImageValoration.of( valoration ) : null;
        return new Image(
                ImageId.of( id ),
                favorite,
                date,
                ImageName.of( name ),
                ImageFileName.of( fileName ),
                imageDescripion,
                imageValoration,
                Set.copyOf( categories ),
                Set.copyOf( tags ),
                createdAt,
                updatedAt
        );
    }



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
// ---| OPTIONAL GETTERS METHODS |----------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<ImageDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<ImageValoration> getImageValoration() {
        return Optional.ofNullable( this.imageValoration );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
