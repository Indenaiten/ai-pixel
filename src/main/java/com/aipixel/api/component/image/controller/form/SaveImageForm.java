package com.aipixel.api.component.image.controller.form;

import com.aipixel.api.common.controller.Form;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
@Getter
@Builder
@AllArgsConstructor
public class SaveImageForm implements Form {

    @Serial
    private static final long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    @NotNull( message = "El nombre de la imagen es requerido" )
    private final String name;

    @NotNull( message = "La imagen es requerida" )
    private final transient MultipartFile image;

    @Getter( AccessLevel.NONE )
    private Boolean favorite;

    private String date;
    private String description;
    private Short valoration;
    private List<Long> categories;
    private List<Long> tags;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public boolean isFavorite() {
        return this.favorite != null && this.favorite;
    }

    public Optional<String> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<String> getDescription(){
        return Optional.ofNullable( this.description );
    }

    public Optional<Short> getValoration(){
        return Optional.ofNullable( this.valoration );
    }

    public List<Long> getCategories() {
        return Optional.ofNullable( this.categories ).orElse( Collections.emptyList() );
    }

    public List<Long> getTags() {
        return Optional.ofNullable( this.tags ).orElse( Collections.emptyList() );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static SaveImageFormBuilder builder( final String name, final MultipartFile image ) {
        if( name == null ) throw new IllegalArgumentException( "El nombre de la imagen es requerido" );
        if( image == null ) throw new IllegalArgumentException( "La imagen es requerida" );

        return new SaveImageFormBuilder().name( name ).image( image );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
