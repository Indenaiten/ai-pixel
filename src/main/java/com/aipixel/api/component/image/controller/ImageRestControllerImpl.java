package com.aipixel.api.component.image.controller;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageMapper;
import com.aipixel.api.component.image.ImageRestController;
import com.aipixel.api.component.image.ImageService;
import com.aipixel.api.component.image.controller.dto.ImageDto;
import com.aipixel.api.component.image.controller.form.SaveImageForm;
import com.aipixel.api.component.image.service.request.SaveImageRequest;
import com.aipixel.api.component.image.vo.ImageId;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



/**
 * Implementación del controlador REST {@link ImageRestController}.
 */
@RestController
public class ImageRestControllerImpl implements ImageRestController {

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
        final List<ImageDto> result = this.imageService.findAll()
            .stream().map( ImageMapper::entityToImageDto ).toList();
        return ApiResponse.success().build( result );
    }


    @SneakyThrows
    @Override
    public ApiResponse<ImageDto> findById( final String id ) {
        final ImageId imageId = ImageId.of( id );
        final Image image = this.imageService.findById( imageId );
        return ApiResponse.success().build( ImageMapper.entityToImageDto( image ));
    }


    @SneakyThrows
    @Override
    public ApiResponse<String> save( final SaveImageForm data ) {
        final SaveImageRequest request = ImageMapper.formToSaveImageRequest( data );
        final ImageId result = this.imageService.saveImage( request );
        return ApiResponse.success().build( result.toString() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
