package com.aipixel.api.component.tag;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.tag.vo.TagId;
import com.aipixel.api.component.tag.vo.TagName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;



/**
 * Clase que representa una etiqueta de imágenes.
 *
 * @see Entity
 */
@Data
@AllArgsConstructor
public class Tag implements Entity {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El identificador de la etiqueta. */
    private final TagId id;

    /** El nombre de la etiqueta. */
    private TagName name;

    /** La fecha y hora de creación de la etiqueta. */
    private final LocalDateTime createdAt;

    /** La fecha y hora de la última actualización de la etiqueta. */
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de una etiqueta con los datos proporcionados.
     *
     * @param id El identificador de la etiqueta.
     * @param name El nombre de la etiqueta.
     * @param createdAt La fecha y hora de creación de la etiqueta.
     * @param updatedAt La fecha y hora de la última actualización de la etiqueta.
     *
     * @return Una nueva instancia de una etiqueta con los datos proporcionados.
     */
    public static Tag of(
            final long id,
            final String name,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){

        final TagId idVo = TagId.of( id );
        final TagName nameVo = TagName.of( name );

        return new Tag(
                idVo,
                nameVo,
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
        final Tag tag = (Tag) o;
        return Objects.equals( this.id, tag.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
