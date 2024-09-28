package com.aipixel.api.component.category;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.category.vo.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;



/**
 * Clase que representa una categoría de imágenes.
 *
 * @see Entity
 */
@Getter
@Builder
@AllArgsConstructor
public class Category implements Entity {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final CategoryId id;
    private final CategoryName name;
    private CategoryDescription description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        final Category category = (Category) o;
        return Objects.equals( this.id, category.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<CategoryDescription> getDescription() {
        return Optional.ofNullable( this.description );
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

    public static CategoryBuilder builder( final CategoryId id, final CategoryName name ) {
        return new CategoryBuilder().id( id ).name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
