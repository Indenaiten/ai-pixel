package com.aipixel.api.component.tag.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.util.Optional;



/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Tag}.
 *
 * @See DTO
 */
@Getter
@Builder
@AllArgsConstructor
public class TagDto implements DTO {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private Long id;
    private String name;
    private String createdAt;
    private String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<String> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<String> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static TagDtoBuilder builder( final Long id, final String name ) {
        return new TagDtoBuilder().id( id ).name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
