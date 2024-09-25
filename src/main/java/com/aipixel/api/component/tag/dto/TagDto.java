package com.aipixel.api.component.tag.dto;

import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;



/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Tag}.
 *
 * @See Serializable
 */
@Data
@AllArgsConstructor
public class TagDto implements Serializable {

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

    /** El identificador de la etiqueta. */
    private final long id;

    /** El nombre de la etiqueta. */
    private final String name;

    /** La fecha y hora de creación de la etiqueta. */
    private final String createdAt;

    /** La fecha y hora de la última actualización de la etiqueta. */
    private final String updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir de la entidad de la {@link Tag}.
     *
     * @param tag La etiqueta.
     */
    public TagDto( final Tag tag ){
        this.id = tag.getId().value();
        this.name = tag.getName().value();
        this.createdAt = tag.getCreatedAt().format( DATE_TIME_FORMATTER );
        this.updatedAt = tag.getUpdatedAt().format( DATE_TIME_FORMATTER );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
    
}
