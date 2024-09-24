package com.aipixel.api.component.tag.dto;

import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class TagDto {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );

// ------------------------------------------------------------------------------------------------------------------ \\

    private final long id;
    private final String name;
    private final String createdAt;
    private final String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public TagDto( final Tag tag ){
        this.id = tag.getId().value();
        this.name = tag.getName().value();
        this.createdAt = tag.getCreatedAt().format( DATE_TIME_FORMATTER );
        this.updatedAt = tag.getUpdatedAt().format( DATE_TIME_FORMATTER );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
    
}
