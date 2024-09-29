package com.aipixel.api.component.image.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.category.controller.dto.CategoryDto;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.tag.controller.dto.TagDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
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
public class ImageDto implements DTO {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String id;

    @Getter( AccessLevel.NONE )
    private Boolean favorite;

    private String date;
    private final String name;
    private final String fileName;
    private String description;
    private Short valoration;
    private List<CategoryDto> categories;
    private List<TagDto> tags;
    private String createdAt;
    private String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }
    
    public Optional<String> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<Short> getValoration() {
        return Optional.ofNullable( this.valoration );
    }

    public Optional<String> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<String> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageDtoBuilder builder( final String id, final String name, final String fileName ) {
        if( id == null ) throw new IllegalArgumentException( "El identificador de la imagen es requerido" );
        if( name == null ) throw new IllegalArgumentException( "El nombre de la imagen es requerido" );
        if( fileName == null ) throw new IllegalArgumentException( "El nombre del archivo de la imagen es requerido" );

        return new ImageDtoBuilder().id( id ).name( name ).fileName( fileName );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
