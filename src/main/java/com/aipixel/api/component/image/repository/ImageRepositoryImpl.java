package com.aipixel.api.component.image.repository;

import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.vo.ImageId;
import com.aipixel.api.component.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    private final ImageDao imageDao;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Autowired
    public ImageRepositoryImpl( final ImageDao imageDao ) {
        this.imageDao = imageDao;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public Optional<Image> findById( final ImageId id ) {
        return Optional.empty();
    }

    @Override
    public List<Image> findAll() {
        return this.imageDao.findAll().stream().map( ImageModel::toEntity ).collect( Collectors.toList() );
    }

    @Override
    public List<Image> find( final String name ) {
        return List.of();
    }

    @Override
    public List<Image> findByCategory( final Category category ) {
        return List.of();
    }

    @Override
    public List<Image> findByTags( final List<Tag> tags ) {
        return List.of();
    }

    @Override
    public void save( final Image image ) {

    }

    @Override
    public void update( final Image image ) {

    }

    @Override
    public void delete( final ImageId id ) {

    }

    @Override
    public void delete( final Image image ) {

    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
