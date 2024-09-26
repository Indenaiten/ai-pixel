package com.aipixel.api.component.image;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.vo.*;
import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;



/**
 * Clase que representa una imagen en la aplicación.
 *
 * @see Entity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Entity {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private ImageId id;
    private Boolean favorite;
    private LocalDate date;
    private ImageName name;
    private ImageFileName fileName;
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

    public Optional<ImageId> getId() {
        return Optional.ofNullable( this.id );
    }

    public Boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<ImageName> getName() {
        return Optional.ofNullable( this.name );
    }

    public Optional<ImageFileName> getFileName() {
        return Optional.ofNullable( this.fileName );
    }

    public Optional<ImageDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<ImageValoration> getImageValoration() {
        return Optional.ofNullable( this.imageValoration );
    }

    public List<Category> getCategories(){
        return this.categories != null ? List.copyOf( this.categories ) : Collections.emptyList();
    }

    public List<Tag> getTags(){
        return this.tags != null ? List.copyOf( this.tags ) : Collections.emptyList();
    }

    public Optional<LocalDateTime> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
