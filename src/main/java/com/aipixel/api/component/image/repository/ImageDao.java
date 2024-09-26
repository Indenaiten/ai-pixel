package com.aipixel.api.component.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



/**
 * DAO que proporciona operaciones de persistencia relacionadas con las imágenes.
 *
 * @See JpaRepository
 * @See ImageModel
 * @See String
 */
@Component
public interface ImageDao extends JpaRepository<ImageModel, String> {

}
