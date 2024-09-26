package com.aipixel.api.component.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



/**
 * DAO que proporciona operaciones de persistencia relacionadas con las categorías.
 *
 * @See JpaRepository
 * @See CategoryModel
 * @See Long
 */
@Component
public interface CategoryDao extends JpaRepository<CategoryModel, Long> {

}
