package com.aipixel.api.component.image;

import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.image.vo.ImageId;
import com.aipixel.api.component.tag.Tag;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    Optional<Image> findById( ImageId id );

    List<Image> findAll();

    List<Image> find( String name );

    List<Image> findByCategory( Category category );

    List<Image> findByTags( List<Tag> tags );

    void save( Image image );

    void update( Image image );

    void delete( ImageId id );

    void delete( Image image );

}
