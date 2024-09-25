package com.aipixel.api.component.image;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.dto.ImageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



/**
 * Controlador REST que proporciona operaciones relacionadas con las imágenes.
 */
@RequestMapping( "/image" )
public interface ImageRestController {


    /**
     * Obtiene todas las imágenes como entidades de tipo {@link ImageDto}.
     *
     * @return Una {@link ApiResponse} con una {@link List} de tipo {@link ImageDto} con todas las imágenes.
     */
    @GetMapping( "/find/all" )
    ApiResponse<List<ImageDto>> findAll();

}
