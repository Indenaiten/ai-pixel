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
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveImageRequest implements ServiceRequest {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    @NotNull( message = "The name of the image is required" )
    private ImageName name;

    @NotNull( message = "The name of the image is required" )
    private String fileName;

    @NotNull( message = "The content of the image is required" )
    private byte[] fileContent;

    @NotNull( message = "The content type of the image is required" )
    private String fileContentType;

    private Boolean favorite = false;
    private LocalDate date;
    private ImageDescription description;
    private ImageValoration imageValoration;
    private Set<CategoryId> categories = Collections.emptySet();
    private Set<TagId> tags = Collections.emptySet();



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }

    public ImageName getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public byte[] getFileContent() {
        return this.fileContent;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public Optional<ImageDescription> getDescription() {
        return Optional.ofNullable( this.description );
    }

    public Optional<ImageValoration> getImageValoration() {
        return Optional.ofNullable( this.imageValoration );
    }

    public List<CategoryId> getCategories() {
        return this.categories != null ? List.copyOf( categories ) : Collections.emptyList();
    }

    public List<TagId> getTags() {
        return this.tags != null ? List.copyOf( tags ) : Collections.emptyList();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
