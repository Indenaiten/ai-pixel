package com.aipixel.api.component.image;

import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.service.request.SaveImageServiceRequest;
import com.aipixel.api.component.image.vo.ImageId;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


/**
 * Servicio que proporciona operaciones relacionadas con las imágenes.
 */
@Validated
public interface ImageService {


    /**
     * Busca y recupera todas las imágenes a partir de los parámetros de paginación.
     *
     * @param lastId El identificador de la última imagen recuperada. Si es {@code null}, se recuperarán las primeras
     *               imágenes.
     * @param limit El límite de imágenes a recuperar. Si es {@code null}, se usará el límite por defecto de la aplicación.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes encontradas en función de los parámetros de
     * paginación.
     */
    List<Image> findAll( ImageId lastId, Integer limit ) throws ImageNotFoundException;


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
     * @param request Los datos necesarios para guardar la imagen enviados en un {@link SaveImageServiceRequest}.
     *
     * @return El identificador de la imagen guardada como un {@link ImageId}.
     */
    ImageId saveImage( @Valid SaveImageServiceRequest request );

}
