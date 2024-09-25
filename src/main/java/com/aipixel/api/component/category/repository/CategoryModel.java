package com.aipixel.api.component.category.repository;

import com.aipixel.api.common.entity.Entityable;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;



/**
 * Clase que representa una categoría de imágenes en la Base de Datos.
 * Se puede convertir en una entidad {@link Category} de la aplicación.
 *
 * @see Entityable
 */
@Entity
@Table( name = "categories" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements Entityable<Category> {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    /** El identificador de la categoría. */
    private long id;

    @Column( name = "name", nullable = false, length = CategoryName.MAX_SIZE, unique = true )
    /** El nombre de la categoría. */
    private String name;

    @Column( name = "description", nullable = false, length = CategoryDescription.MAX_SIZE )
    /** La descripción de la categoría. */
    private String description;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false )
    /** La fecha y hora de creación de la categoría. */
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    /** La fecha y hora de la última actualización de la categoría. */
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir de una entidad {@link Category}.
     *
     * @param category La entidad {@link Category} a partir de la cual se crea la instancia.
     */
    public CategoryModel( final Category category ){
        this.id = category.getId().value();
        this.name = category.getName().value();
        category.getDescription().ifPresent( description -> this.description = description.value() );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public Category toEntity() {
        return Category.of(
                this.id,
                this.name,
                this.description,
                this.createdAt.toLocalDateTime(),
                this.updatedAt.toLocalDateTime()
        );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
