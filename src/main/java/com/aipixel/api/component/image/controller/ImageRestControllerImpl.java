package com.aipixel.api.component.image.controller;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.ImageRestController;
import com.aipixel.api.component.image.ImageService;
import com.aipixel.api.component.image.dto.ImageDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;



/**
 * Implementación del controlador REST {@link ImageRestController}.
 */
@RestController
public class ImageRestControllerImpl implements ImageRestController {

    /** El servicio de imágenes. */
    private final ImageService imageService;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param imageService El servicio de imágenes.
     */
    public ImageRestControllerImpl( final ImageService imageService ) {
        this.imageService = imageService;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENTED METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public ApiResponse<List<ImageDto>> findAll() {
        final List<ImageDto> result = this.imageService.getImages().stream().map( ImageDto::new ).collect( Collectors.toList() );
        return ApiResponse.success().build( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
