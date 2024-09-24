package com.aipixel.api.component.image.dto;

import com.aipixel.api.component.category.dto.CategoryDto;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.tag.dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ImageDto {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String id;
    private final boolean favorite;
    private final String date;
    private final String name;
    private final String fileName;
    private final String description;
    private final short valoration;
    private final List<CategoryDto> categories;
    private final List<TagDto> tags;
    private final String createdAt;
    private final String updatedAt;

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageDto( final Image image ){
        this.id = image.getId().toString();
        this.favorite = image.getFavorite();
        if( image.getDate().isPresent() ) this.date = image.getDate().get().format( DATE_FORMATTER );
        else this.date = null;
        this.name = image.getName().toString();
        this.fileName = image.getFileName().toString();
        if( image.getDescription().isPresent() ) this.description = image.getDescription().get().toString();
        else this.description = null;
        if( image.getImageValoration().isPresent() ) this.valoration = image.getImageValoration().get().value();
        else this.valoration = 0;
        this.categories = image.getCategories().stream().map( CategoryDto::new ).collect( Collectors.toList() );
        this.tags = image.getTags().stream().map( TagDto::new ).collect( Collectors.toList() );
        this.createdAt = image.getCreatedAt().format( DATE_TIME_FORMATTER );
        this.updatedAt = image.getUpdatedAt().format( DATE_TIME_FORMATTER );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
