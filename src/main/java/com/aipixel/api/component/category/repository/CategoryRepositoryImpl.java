package com.aipixel.api.component.category.repository;

import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.category.CategoryMapper;
import com.aipixel.api.component.category.CategoryRepository;
import com.aipixel.api.component.category.vo.CategoryId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * Implementación del repositorio {@link CategoryRepository}.
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDao categoryDao;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param categoryDao El DAO de categorías.
     */
    @Autowired
    public CategoryRepositoryImpl( final CategoryDao categoryDao ) {
        this.categoryDao = categoryDao;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public List<Category> findAllById( final List<CategoryId> id ) {
        final List<Long> tagIdList = id.stream().map( CategoryId::value ).toList();
        return this.categoryDao.findAllById( tagIdList )
                .stream().map( CategoryMapper::modelToEntity ).toList();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
