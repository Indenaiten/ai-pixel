package com.aipixel.api.component.image;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.vo.*;
import com.aipixel.api.component.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;



/**
 * Clase que representa una imagen en la aplicación.
 *
 * @see Entity
 */
@Data
@AllArgsConstructor
public class Image implements Entity {

    /**
     * Un identificador de versión único para la clase Serializable. Este ID se utiliza durante el proceso de
     * deserialización para verificar que el remitente y el receptor de un objeto serializado mantengan versiones
     * compatibles de la clase, asegurando que el objeto se pueda deserializar correctamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    /** El identificador de la imagen. */
    private final ImageId id;

    /** Indica si la imagen es favorita. */
    private Boolean favorite;

    /** La fecha de la imagen. */
    private LocalDate date;

    /** El nombre de la imagen. */
    private ImageName name;

    /** El nombre del archivo de la imagen. */
    private ImageFileName fileName;

    /** La descripción de la imagen. */
    private ImageDescription description;

    /** La valoración de la imagen. */
    private ImageValoration imageValoration;

    /** Las categorías de la imagen. */
    private Set<Category> categories;

    /** Las etiquetas de la imagen. */
    private Set<Tag> tags;

    /** La fecha y hora de creación de la imagen. */
    private final LocalDateTime createdAt;

    /** La fecha y hora de la última actualización de la imagen. */
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea una nueva instancia de una imagen con los datos proporcionados.
     *
     * @param id El identificador de la imagen.
     * @param favorite Indica si la imagen es favorita.
     * @param date La fecha de la imagen.
     * @param name El nombre de la imagen.
     * @param fileName El nombre del archivo de la imagen.
     * @param description La descripción de la imagen.
     * @param valoration La valoración de la imagen.
     * @param categories Las categorías de la imagen.
     * @param tags Las etiquetas de la imagen.
     * @param createdAt La fecha y hora de creación de la imagen.
     * @param updatedAt La fecha y hora de la última actualización de la imagen.
     *
     * @return Una nueva instancia de una imagen con los datos proporcionados.
     */
    public static Image of(
            final String id,
            final Boolean favorite,
            final LocalDate date,
            final String name,
            final String fileName,
            final String description,
            final Short valoration,
            final List<Category> categories,
            final List<Tag> tags,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){

        final ImageId idVo = ImageId.of( id );
        final ImageName nameVo = ImageName.of( name );
        final ImageFileName fileNameVo = ImageFileName.of( fileName );
        final ImageDescription descriptionVo = description != null ? ImageDescription.of( description ) : null;
        final ImageValoration valorationVo = valoration != null ? ImageValoration.of( valoration ) : null;
        final Set<Category> categoriesSet = Set.copyOf( categories );
        final Set<Tag> tagsSet = Set.copyOf( tags );

        return new Image(
                idVo,
                favorite,
                date,
                nameVo,
                fileNameVo,
                descriptionVo,
                valorationVo,
                categoriesSet,
                tagsSet,
                createdAt,
                updatedAt
        );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        final Image image = (Image) o;
        return Objects.equals( this.id, image.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OPTIONAL GETTERS METHODS |----------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene la fecha de la imagen si dispone de ella.
     *
     * @return La fecha de la imagen si dispone de ella.
     */
    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }


    /**
     * Obtiene la descripción de la imagen si dispone de ella.
     *
     * @return La descripción de la imagen si dispone de ella.
     */
    public Optional<ImageDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }


    /**
     * Obtiene la valoración de la imagen si dispone de ella.
     *
     * @return La valoración de la imagen si dispone de ella.
     */
    public Optional<ImageValoration> getImageValoration() {
        return Optional.ofNullable( this.imageValoration );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
