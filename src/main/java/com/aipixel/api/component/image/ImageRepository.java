package com.aipixel.api.component.image;

import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.vo.ImageId;

import java.util.List;
import java.util.Optional;



/**
 * Repositorio que proporciona operaciones de persistencia relacionadas con las imágenes.
 */
public interface ImageRepository {

    /**
     * Busca y recupera todas las imágenes en función de los parámetros de paginación.
     *
     * @param lastId El identificador de la última imagen recuperada. Si es {@code null}, se recuperarán las primeras
     *               imágenes.
     * @param limit El límite de imágenes a recuperar.
     *
     * @return Una {@link List} de tipo {@link Image} con todas las imágenes encontradas en función de los parámetros de
     * paginación.
     *
     * @throws ImageNotFoundException Si no se encuentra la imagen a partir del identificador proporcionado.
     */
    List<Image> findAll( ImageId lastId, int limit ) throws ImageNotFoundException;


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
