package com.aipixel.api.component.category.repository;

import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    private long id;

    @Column( name = "name", nullable = false, length = CategoryName.MAX_SIZE, unique = true )
    private String name;

    @Column( name = "description", length = CategoryDescription.MAX_SIZE )
    private String description;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private Timestamp createdAt;

    @UpdateTimestamp
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

}
