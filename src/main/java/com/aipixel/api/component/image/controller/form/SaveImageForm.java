package com.aipixel.api.component.image.controller.form;

import com.aipixel.api.common.controller.Form;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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

    private boolean favorite;
    private String date;
    private String description;
    private Short valoration;
    private List<Long> categories;
    private List<Long> tags;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<String> getDate() {
        return Optional.ofNullable( this.date );
    }

    public Optional<String> getDescription(){
        return Optional.ofNullable( this.description );
    }

    public Optional<Short> getValoration(){
        return Optional.ofNullable( this.valoration );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static SaveImageFormBuilder builder( final String name, final MultipartFile image ) {
        return new SaveImageFormBuilder().name( name ).image( image );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
