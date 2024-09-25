package com.aipixel.api.component.image;

import java.util.List;



/**
 * Servicio que proporciona operaciones relacionadas con las imágenes.
 */
public interface ImageService {


    /**
     * Obtiene todas las imágenes como entidades de tipo {@link Image}.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes.
     */
    List<Image> getImages();

}
