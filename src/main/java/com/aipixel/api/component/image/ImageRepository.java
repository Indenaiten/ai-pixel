package com.aipixel.api.component.image;

import java.util.List;



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

}
