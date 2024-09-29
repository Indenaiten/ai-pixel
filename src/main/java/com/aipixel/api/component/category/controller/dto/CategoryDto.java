package com.aipixel.api.component.category.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Optional;


/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Category}.
 *
 * @See DTO
 */
@Getter
@Builder
@AllArgsConstructor
public class CategoryDto implements DTO {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final long id;
    private final String name;
    private String description;
    private String createdAt;
    private String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<String> getDescription() {
        return Optional.ofNullable( this.description );
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

    public static CategoryDtoBuilder builder( final Long id, final String name ) {
        if( id == null ) throw new IllegalArgumentException( "El identificador de la categoría es requerido" );
        if( name == null ) throw new IllegalArgumentException( "El nombre de la categoría es requerido" );

        return new CategoryDtoBuilder().id( id ).name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
