package com.aipixel.api.component.category.repository;

import com.aipixel.api.common.entity.Entityable;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryName;
import com.aipixel.api.component.image.repository.ImageModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table( name = "categories" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements Entityable<Category> {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    private long id;

    @Column( name = "name", nullable = false, length = CategoryName.MAX_SIZE, unique = true )
    private String name;

    @Column( name = "description", nullable = false, length = CategoryDescription.MAX_SIZE )
    private String description;

    @ManyToMany( mappedBy = "categories", fetch = FetchType.LAZY )
    private Set<ImageModel> images;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false )
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

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
