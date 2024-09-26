package com.aipixel.api.component.image.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.category.controller.dto.CategoryDto;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.tag.controller.dto.TagDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Collections;
import java.util.List;


/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Image}.
 *
 * @See DTO
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto implements DTO {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String IMAGES_URL = "/api/resources/images";

// ------------------------------------------------------------------------------------------------------------------ \\

    private String id;
    private Boolean favorite;
    private String date;
    private String name;
    private String fileName;
    private String description;
    private Short valoration;
    private List<CategoryDto> categories;
    private List<TagDto> tags;
    private String createdAt;
    private String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @JsonProperty( "url" )
    public String getUrl(){
        return String.format( "%s/%s", IMAGES_URL, this.getFileName() );
    }

    @JsonProperty( "categories" )
    public List<CategoryDto> getCategories(){
        return this.categories != null ? List.copyOf( this.categories ) : Collections.emptyList();
    }

    @JsonProperty( "tags" )
    public List<TagDto> getTags(){
        return this.tags != null ? List.copyOf( this.tags ) : Collections.emptyList();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
