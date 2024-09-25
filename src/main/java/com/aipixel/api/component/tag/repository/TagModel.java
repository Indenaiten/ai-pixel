package com.aipixel.api.component.tag.repository;

import com.aipixel.api.common.entity.Entityable;
import com.aipixel.api.component.tag.Tag;
import com.aipixel.api.component.tag.vo.TagName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;



/**
 * Clase que representa una etiqueta de imágenes en la Base de Datos.
 * Se puede convertir a una entidad de tipo {@link Tag}.
 *
 * @see Entityable
 */
@Entity
@Table( name = "tags" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagModel implements Entityable<Tag> {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    /** El identificador de la etiqueta. */
    private long id;

    @Column( name = "name", nullable = false, length = TagName.MAX_SIZE, unique = true )
    /** El nombre de la etiqueta. */
    private String name;

    @Column( name = "created_at", nullable = false )
    /** La fecha y hora de creación de la etiqueta. */
    private Timestamp createdAt;

    @Column( name = "updated_at", nullable = false )
    /** La fecha y hora de la última actualización de la etiqueta. */
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir de una entidad de tipo {@link Tag}.
     *
     * @param tag La entidad de tipo {@link Tag}.
     */
    public TagModel( final Tag tag ){
        this.id = tag.getId().value();
        this.name = tag.getName().value();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public Tag toEntity() {
        return Tag.of(
                this.id,
                this.name,
                this.createdAt.toLocalDateTime(),
                this.updatedAt.toLocalDateTime()
        );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
