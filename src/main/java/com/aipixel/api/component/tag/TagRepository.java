package com.aipixel.api.component.tag;

import com.aipixel.api.component.tag.vo.TagId;
import com.aipixel.api.component.tag.vo.TagName;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Optional<Tag> findById( TagId id );

    Optional<Tag> findByName( TagName name );

    List<Tag> findAll();

    List<Tag> find( String name );

    void save( Tag tag );

    void update( Tag tag );

    void delete( TagId id );

    void delete( Tag tag );

}
