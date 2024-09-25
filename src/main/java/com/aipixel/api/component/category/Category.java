package com.aipixel.api.component.category;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.category.vo.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;



/**
 * Clase que representa una categoría de imágenes.
 *
 * @see Entity
 */
@Data
@AllArgsConstructor
public class Category implements Entity {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El identificador de la categoría. */
    private final CategoryId id;

    /** El nombre de la categoría. */
    private CategoryName name;

    /** La descripción de la categoría. */
    private CategoryDescription description;

    /** La fecha y hora de creación de la categoría. */
    private final LocalDateTime createdAt;

    /** La fecha y hora de la última actualización de la categoría. */
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de una categoría con los datos proporcionados.
     *
     * @param id El identificador de la categoría.
     * @param name El nombre de la categoría.
     * @param description La descripción de la categoría.
     * @param createdAt La fecha y hora de creación de la categoría.
     * @param updatedAt La fecha y hora de la última actualización de la categoría.
     *
     * @return Una nueva instancia de una categoría con los datos proporcionados.
     */
    public static Category of(
            final long id,
            final String name,
            final String description,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){

        final CategoryId idVo = CategoryId.of( id );
        final CategoryName nameVo = CategoryName.of( name );
        final CategoryDescription descriptionVo = description != null ? CategoryDescription.of( description ) : null;

        return new Category(
                idVo,
                nameVo,
                descriptionVo,
                createdAt,
                updatedAt
        );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Determina si otro objeto es igual a esta categoría.
     * Dos categorías se consideran iguales si son del mismo tipo y sus identificadores son iguales.
     *
     * @param o El objeto con el que se comparará esta categoría para determinar si son iguales.
     *
     * @return {@code true} si el identificador del objeto especificado es igual al identificador de esta categoría;
     * {@code false} en caso contrario.
     */
    @Override
    public boolean equals( final Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        final Category category = (Category) o;
        return Objects.equals( this.id, category.id );
    }


    /**
     * Calcula el código hash para esta categoría basado en su identificador.
     * Esto asegura que las categorías con el mismo identificador tendrán el mismo código hash.
     *
     * @return El código hash del identificador de esta categoría.
     */
    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OPTIONAL GETTERS METHODS |----------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene el nombre de la categoría si dispone de él.
     *
     * @return El nombre de la categoría si dispone de él.
     */
    public Optional<CategoryDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
