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
@Getter
@Builder
@AllArgsConstructor
public class Tag implements Entity {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final TagId id;
    private final TagName name;
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

    public Optional<LocalDateTime> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static TagBuilder builder( final TagId id, final TagName name ) {
        if( id == null ) throw new IllegalArgumentException( "El identificador de la etiqueta no puede ser nulo" );
        if( name == null ) throw new IllegalArgumentException( "El nombre de la etiqueta no puede ser nulo" );

        return new TagBuilder().id( id ).name( name );
    }

    public static TagBuilder create( final TagName name ) {
        if( name == null ) throw new IllegalArgumentException( "No se puede crear una etiqueta sin nombre" );

        return new TagBuilder().name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
