package com.aipixel.api.component.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



/**
 * DAO que proporciona operaciones de persistencia relacionadas con las etiquetas.
 *
 * @See JpaRepository
 * @See TagModel
 * @See Long
 */
@Component
public interface TagDao extends JpaRepository<TagModel, Long> {

}
