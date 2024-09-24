package com.aipixel.api.component.category;

import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.category.vo.CategoryName;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById( CategoryId id );

    Optional<Category> findByName( CategoryName name );

    List<Category> findAll();

    List<Category> find( String name );

    void save( Category category );

    void update( Category category );

    void delete( CategoryId id );

    void delete( Category category );

}
