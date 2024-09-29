package com.aipixel.api.component.image.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


/**
 * DAO que proporciona operaciones de persistencia relacionadas con las imágenes.
 *
 * @See JpaRepository
 * @See ImageModel
 * @See String
 */
@Component
public interface ImageDao extends JpaRepository<ImageModel, String> {

    /**
     * Busca y recupera las imágenes cuya fecha de creación sea anterior a la indicada en base a la paginación.
     *
     * @param createdAt La fecha de creación a partir de la cual se buscan las imágenes.
     * @param pageable La paginación a aplicar.
     *
     * @return La lista de imágenes encontradas que cumplen con los criterios de búsqueda.
     */
    List<ImageModel> findByCreatedAtLessThan( Timestamp createdAt, Pageable pageable );

}
