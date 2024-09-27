package com.aipixel.api.component.image;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.controller.dto.ImageDto;
import com.aipixel.api.component.image.controller.form.SaveImageForm;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * Controlador REST que proporciona las operaciones relacionadas con las imágenes.
 */
@RequestMapping( "/image" )
public interface ImageRestController {

    /**
     * Obtiene la URL base de las imágenes.
     *
     * @return Una {@link ApiResponse} con la URL base de la imagen.
     */
    @GetMapping( "/url" )
    ApiResponse<String> getUrlBaseImage( );


    /**
     * Obtiene todas las imágenes como entidades de tipo {@link ImageDto}.
     *
     * @return Una {@link ApiResponse} con una {@link List} de tipo {@link ImageDto} con todas las imágenes encontradas.
     */
    @GetMapping( "/find/all" )
    ApiResponse<List<ImageDto>> findAll();


    /**
     * Obtiene una imagen por su identificador.
     *
     * @param id El identificador de la imagen.
     *
     * @return Una {@link ApiResponse} con un {@link ImageDto}.
     */
    @GetMapping( "/find/id/{id}" )
    ApiResponse<ImageDto> findById( @PathVariable( "id" ) String id );


    /**
     * Guarda una imagen en la aplicación.
     *
     * @param data Los datos de la imagen a guardar.
     *
     * @return Una {@link ApiResponse} con un mensaje de confirmación.
     */
    @PostMapping( value = "/save" )
    ApiResponse<String> save( @Valid @ModelAttribute() SaveImageForm data );

}
