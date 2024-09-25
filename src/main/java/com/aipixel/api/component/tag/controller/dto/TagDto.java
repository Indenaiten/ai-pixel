package com.aipixel.api.component.tag.controller.dto;

import com.aipixel.api.common.controller.DTO;
import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Tag}.
 *
 * @See DTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public Optional<Long> getId() {
        return Optional.ofNullable( this.id );
    }

    public Optional<String> getName() {
        return Optional.ofNullable( this.name );
    }

    public Optional<String> getCreatedAt() {
        return Optional.ofNullable( this.createdAt );
    }

    public Optional<String> getUpdatedAt() {
        return Optional.ofNullable( this.updatedAt );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
