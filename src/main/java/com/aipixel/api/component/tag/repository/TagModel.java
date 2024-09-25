package com.aipixel.api.component.tag.repository;

import com.aipixel.api.component.tag.vo.TagName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Optional;



/**
 * Clase que representa una etiqueta de imágenes en la Base de Datos.
 */
@Entity
@Table( name = "tags" )
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagModel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    private Long id;

    @Column( name = "name", nullable = false, length = TagName.MAX_SIZE, unique = true )
    private String name;

    @Column( name = "created_at", nullable = false, updatable = false )
    private Timestamp createdAt;

    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<Long> getId() {
        return Optional.ofNullable( this.id );
    }

    public Optional<String> getName() {
        return Optional.ofNullable( this.name );
    }

    public Optional<Timestamp> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<Timestamp> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
