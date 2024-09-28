package com.aipixel.api.component.image.service.request;

import com.aipixel.api.common.service.request.ServiceRequest;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.image.vo.ImageDescription;
import com.aipixel.api.component.image.vo.ImageName;
import com.aipixel.api.component.image.vo.ImageValoration;
import com.aipixel.api.component.tag.vo.TagId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;



/**
 * Clase que representa una petición para guardar una imagen en la aplicación.
 *
 * @See ServiceRequest
 */
@Getter
@Builder
@AllArgsConstructor
public class SaveImageServiceRequest implements ServiceRequest {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    @NotNull( message = "El nombre de la imagen es requerido" )
    private final ImageName name;

    @NotNull( message = "El nombre del archivo de la imagen es requerido" )
    private final String fileName;

    @NotNull( message = "El contenido de la imagen es requerido" )
    private final byte[] fileContent;

    @NotNull( message = "El tipo de contenido de la imagen es requerido" )
    private final String fileContentType;

    private boolean favorite;
    private LocalDate date;
    private ImageDescription description;
    private ImageValoration imageValoration;
    private Set<CategoryId> categories;
    private Set<TagId> tags;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<ImageDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<ImageValoration> getImageValoration() {
        return Optional.ofNullable( this.imageValoration );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static SaveImageServiceRequestBuilder builder(
            final ImageName name,
            final String fileName,
            final byte[] fileContent,
            final String fileContentType
    ) {
        return new SaveImageServiceRequestBuilder()
                .name( name )
                .fileName( fileName )
                .fileContent( fileContent )
                .fileContentType( fileContentType );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
