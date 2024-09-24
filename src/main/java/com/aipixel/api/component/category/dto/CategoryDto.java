package com.aipixel.api.component.category.dto;

import com.aipixel.api.component.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class CategoryDto {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );

// ------------------------------------------------------------------------------------------------------------------ \\

    private final long id;
    private final String name;
    private final String description;
    private final String createdAt;
    private final String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public CategoryDto( final Category category ){
        this.id = category.getId().value();
        this.name = category.getName().value();
        if( category.getDescription().isPresent() ) this.description = category.getDescription().get().value();
        else this.description = null;
        this.createdAt = category.getCreatedAt().format( DATE_TIME_FORMATTER );
        this.updatedAt = category.getUpdatedAt().format( DATE_TIME_FORMATTER );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
