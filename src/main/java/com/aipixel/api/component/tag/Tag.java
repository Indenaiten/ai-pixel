package com.aipixel.api.component.tag;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.tag.vo.TagId;
import com.aipixel.api.component.tag.vo.TagName;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


/**
 * Clase que representa una etiqueta de imágenes.
 *
 * @see Entity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Entity {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private TagId id;
    private TagName name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        final Tag tag = (Tag) o;
        return Objects.equals( this.id, tag.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<TagId> getId() {
        return Optional.ofNullable( this.id );
    }

    public Optional<TagName> getName() {
        return Optional.ofNullable( this.name );
    }

    public Optional<LocalDateTime> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
