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

@Data
@AllArgsConstructor
public class Category implements Entity {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private final CategoryId id;
    private CategoryName name;
    private CategoryDescription description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static Category of(
            final long id,
            final String name,
            final String description,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){
        final CategoryDescription categoryDescription = description != null ? CategoryDescription.of( description ) : null;
        return new Category(
                CategoryId.of( id ),
                CategoryName.of( name ),
                categoryDescription,
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
        final Category category = (Category) o;
        return Objects.equals( this.id, category.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OPTIONAL GETTERS METHODS |----------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<CategoryDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
