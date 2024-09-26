package com.aipixel.api.component.image;

import java.util.List;


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

}
