package com.aipixel.api.component.tag.repository;

import com.aipixel.api.component.tag.Tag;
import com.aipixel.api.component.tag.TagMapper;
import com.aipixel.api.component.tag.TagRepository;
import com.aipixel.api.component.tag.vo.TagId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * Implementación del repositorio {@link TagRepository}.
 */
@Repository
public class TagRepositoryImpl implements TagRepository {

    private final TagDao tagDao;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param tagDao El DAO de etiquetas.
     */
    @Autowired
    public TagRepositoryImpl( final TagDao tagDao ) {
        this.tagDao = tagDao;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public List<Tag> findAllById(final List<TagId> id ) {
        final List<Long> tagIdList = id.stream().map( TagId::value ).toList();
        return this.tagDao.findAllById( tagIdList )
                .stream().map( TagMapper::modelToEntity ).toList();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
