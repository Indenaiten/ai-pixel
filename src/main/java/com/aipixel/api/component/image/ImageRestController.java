package com.aipixel.api.component.image;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.controller.dto.ImageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



/**
 * Controlador REST que proporciona las operaciones relacionadas con las imágenes.
 */
@RequestMapping( "/image" )
public interface ImageRestController {


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


}
