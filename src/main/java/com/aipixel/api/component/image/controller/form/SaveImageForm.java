package com.aipixel.api.component.image.controller.form;

import com.aipixel.api.common.controller.Form;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



/**
 * Clase que representa los datos requeridos en el endpoint para guardar una imagen en la aplicación.
 *
 * @See Form
 */
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveImageForm implements Form {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private Boolean favorite;
    private String date;
    private String name;
    private transient MultipartFile image;
    private String description;
    private Short valoration;
    private List<Long> categories;
    private List<Long> tags;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }

    public Optional<String> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<String> getName() {
        return Optional.ofNullable( this.name );
    }

    public Optional<MultipartFile> getImage() {
        return Optional.ofNullable( this.image );
    }

    public Optional<String> getDescription(){
        return Optional.ofNullable( this.description );
    }

    public Optional<Short> getValoration(){
        return Optional.ofNullable( this.valoration );
    }

    @JsonProperty( "categories" )
    public List<Long> getCategories() {
        return this.categories != null ? List.copyOf( this.categories ) : Collections.emptyList();
    }

    @JsonProperty( "tags" )
    public List<Long> getTags() {
        return this.tags != null ? List.copyOf( this.tags ) : Collections.emptyList();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
