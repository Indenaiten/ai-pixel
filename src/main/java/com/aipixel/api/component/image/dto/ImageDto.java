package com.aipixel.api.component.image.dto;

import com.aipixel.api.component.category.dto.CategoryDto;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.tag.dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Clase que representa un Objeto de Transferencia de Datos (DTO) de una {@link Image}.
 *
 * @See Serializable
 */
@Data
@AllArgsConstructor
public class ImageDto implements Serializable {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** El formato de fecha utilizado para la representación de las fechas. */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );

    /** El formato de fecha y hora utilizado para la representación de las fechas y horas. */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El identificador de la imagen. */
    private final String id;

    /** Indica si la imagen es favorita. */
    private final boolean favorite;

    /** La fecha de la imagen. */
    private final String date;

    /** El nombre de la imagen. */
    private final String name;

    /** El nombre del archivo de la imagen. */
    private final String fileName;

    /** La descripción de la imagen. */
    private final String description;

    /** La valoración de la imagen. */
    private final short valoration;

    /** Los DTO de {@link CategoryDto} de la imagen. */
    private final List<CategoryDto> categories;

    /** Los DTO de {@link TagDto} de la imagen. */
    private final List<TagDto> tags;

    /** La fecha y hora de creación de la imagen. */
    private final String createdAt;

    /** La fecha y hora de la última actualización de la imagen. */
    private final String updatedAt;

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir de la entidad de la {@link Image}.
     *
     * @param image La imagen.
     */
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
