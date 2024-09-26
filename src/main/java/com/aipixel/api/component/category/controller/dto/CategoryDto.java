package com.aipixel.api.component.category.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Optional;


/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Category}.
 *
 * @See DTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements DTO {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private long id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<Long> getId() {
        return Optional.ofNullable( this.id );
    }

    public Optional<String> getName() {
        return Optional.ofNullable( this.name );
    }

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

}
