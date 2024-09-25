package com.aipixel.api.component.category.dto;

import com.aipixel.api.component.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;



/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Category}.
 *
 * @See Serializable
 */
@Data
@AllArgsConstructor
public class CategoryDto implements Serializable {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** El formato de fecha y hora utilizado para la representación de las fechas y horas. */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El identificador de la categoría. */
    private final long id;

    /** El nombre de la categoría. */
    private final String name;

    /** La descripción de la categoría. */
    private final String description;

    /** La fecha y hora de creación de la categoría. */
    private final String createdAt;

    /** La fecha y hora de la última actualización de la categoría. */
    private final String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir de la entidad de la {@link Category}.
     *
     * @param category La categoría.
     */
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
