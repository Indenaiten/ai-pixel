package com.aipixel.api.component.image.service.request;

import com.aipixel.api.common.service.request.ServiceRequest;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.image.vo.ImageDescription;
import com.aipixel.api.component.image.vo.ImageName;
import com.aipixel.api.component.image.vo.ImageValoration;
import com.aipixel.api.component.tag.vo.TagId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveImageRequest implements ServiceRequest {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private Boolean favorite;
    private LocalDate date;
    private ImageName name;
    private String fileName;
    private byte[] fileContent;
    private String fileContentType;
    private ImageDescription description;
    private ImageValoration imageValoration;
    private Set<CategoryId> categories;
    private Set<TagId> tags;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<ImageName> getName() {
        return Optional.ofNullable( this.name );
    }

    public Optional<String> getFileName() {
        return Optional.ofNullable( this.fileName );
    }

    public Optional<byte[]> getFileContent() {
        return Optional.ofNullable( this.fileContent );
    }

    public Optional<String> getFileContentType() {
        return Optional.ofNullable( this.fileContentType );
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
