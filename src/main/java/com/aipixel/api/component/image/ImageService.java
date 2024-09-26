package com.aipixel.api.component.image;

import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.service.request.SaveImageRequest;
import com.aipixel.api.component.image.vo.ImageId;

import java.util.List;
import java.util.Optional;


/**
 * Servicio que proporciona operaciones relacionadas con las imágenes.
 */
public interface ImageService {


    /**
     * Busca y recupera todas las imágenes.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes.
     */
    List<Image> findAll();


    /**
     * Obtiene una imagen por su identificador.
     *
     * @param id El identificador de la imagen de tipo {@link ImageId}.
     *
     * @return Un {@link Optional} de tipo {@link Image}.
     *
     * @throws ImageNotFoundException Si la imagen no existe.
     */
    Image findById( ImageId id ) throws ImageNotFoundException;


    /**
     * Guarda una imagen en la aplicación.
     *
     * @param request Los datos necesarios para guardar la imagen enviados en un {@link SaveImageRequest}.
     *
     * @return El identificador de la imagen guardada como un {@link ImageId}.
     */
    ImageId saveImage( SaveImageRequest request );

}
