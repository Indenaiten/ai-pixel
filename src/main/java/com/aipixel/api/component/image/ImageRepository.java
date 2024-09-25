package com.aipixel.api.component.image;

import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.vo.ImageId;
import com.aipixel.api.component.tag.Tag;

import java.util.List;
import java.util.Optional;



/**
 * Repositorio que proporciona operaciones de persistencia relacionadas con las imágenes.
 */
public interface ImageRepository {

    /**
     * Obtiene todas las imágenes como entidades de tipo {@link Image}.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes.
     */
    List<Image> findAll();

}
