package com.aipixel.api.component.category.repository;

import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryName;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Optional;



/**
 * Clase que representa una categoría de imágenes en la Base de Datos.
 */
@Entity
@Table( name = "categories" )
@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    private long id;
    public static final String FIELD_ID = "id";

    @Column( name = "name", nullable = false, length = CategoryName.MAX_SIZE, unique = true )
    private String name;
    public static final String FIELD_NAME = "name";

    @Column( name = "description", length = CategoryDescription.MAX_SIZE )
    private String description;
    public static final String FIELD_DESCRIPTION = "description";

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private Timestamp createdAt;
    public static final String FIELD_CREATED_AT = "createdAt";

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;
    public static final String FIELD_UPDATED_AT = "updatedAt";



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<String> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<Timestamp> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<Timestamp> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static CategoryModelBuilder builder( final long id, final String name ) {
        return new CategoryModelBuilder().id( id ).name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
