package com.aipixel.api.component.image;

import com.aipixel.api.component.image.vo.ImageId;

import java.util.List;
import java.util.Optional;



/**
 * Repositorio que proporciona operaciones de persistencia relacionadas con las imágenes.
 */
public interface ImageRepository {

    /**
     * Busca y recupera todas las imágenes.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes.
     */
    List<Image> findAll();


    /**
     * Busca y recupera una imagen por su identificador.
     *
     * @param id El identificador de la imagen de tipo {@link ImageId}.
     *
     * @return Un {@link Optional} de tipo {@link Image}.
     */
    Optional<Image> findById( ImageId id );

}
