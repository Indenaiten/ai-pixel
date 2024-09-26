package com.aipixel.api.component.image.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.category.controller.dto.CategoryDto;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.tag.Tag;
import com.aipixel.api.component.tag.controller.dto.TagDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
