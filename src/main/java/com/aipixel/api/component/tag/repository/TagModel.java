package com.aipixel.api.component.tag.repository;

import com.aipixel.api.component.tag.vo.TagName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Optional;



/**
 * Clase que representa una etiqueta de imágenes en la Base de Datos.
 */
@Entity
@Table( name = "tags" )
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagModel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    private Long id;
    public static final String FIELD_ID = "id";

    @Column( name = "name", nullable = false, length = TagName.MAX_SIZE, unique = true )
    private String name;
    public static final String FIELD_NAME = "name";

    @Column( name = "created_at", nullable = false, updatable = false )
    private Timestamp createdAt;
    public static final String FIELD_CREATED_AT = "createdAt";

    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;
    public static final String FIELD_UPDATED_AT = "updatedAt";



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<Timestamp> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<Timestamp> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static TagModelBuilder builder( final Long id, final String name ) {
        return new TagModelBuilder().id( id ).name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
