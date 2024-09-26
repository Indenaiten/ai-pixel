package com.aipixel.api.component.category;

import com.aipixel.api.component.category.vo.CategoryId;

import java.util.List;



/**
 * Repositorio que proporciona operaciones de persistencia relacionadas con las categorías.
 */
public interface CategoryRepository {

    /**
     * Busca categorías a partir de varios identificadores de categorías proporcionados.
     *
     * @param id Los identificadores de las categorías como {@link CategoryId}.
     *
     * @return Las categorías encontradas como {@link Category}.
     */
    List<Category> findAllById( List<CategoryId> id );

}
