package com.aipixel.api.component.image;

import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.vo.ImageId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



/**
 * Repositorio que proporciona operaciones de persistencia relacionadas con las imágenes.
 */
public interface ImageRepository {

    /**
     * Busca y recupera todas las imágenes cuya fecha de creación sea anterior a la indicada y en función de los
     * parámetros de paginación.
     *
     * @param createdAt La fecha de creación a partir de la cual se buscan las imágenes que sean anteriores.
     * @param limit El límite de imágenes a recuperar.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes encontradas cuya fecha de creación sea
     * anterior a la indicada y en función de los parámetros de paginación.
     */
    List<Image> findAll( LocalDateTime createdAt, int limit );


    /**
     * Obtiene el número total de imágenes.
     *
     * @return El número total de imágenes.
     */
    Long countAll();


    /**
     * Busca y recupera una imagen por su identificador.
     *
     * @param id El identificador de la imagen de tipo {@link ImageId}.
     *
     * @return Un {@link Optional} de tipo {@link Image}.
     */
    Optional<Image> findById( ImageId id );


    /**
     * Guarda una imagen en la Base de Datos.
     *
     * @param image La imagen que se va a guardar como entidad de tipo {@link Image}.
     */
    void save( Image image );

}
