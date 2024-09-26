package com.aipixel.api.component.tag;

import com.aipixel.api.component.tag.vo.TagId;

import java.util.List;



/**
 * Repositorio que proporciona operaciones de persistencia relacionadas con las etiquetas.
 */
public interface TagRepository {

    /**
     * Busca etiquetas a partir de varios identificadores de etiquetas proporcionados.
     *
     * @param id Los identificadores de las etiquetas como {@link TagId}.
     *
     * @return Las etiquetas encontradas como {@link Tag}.
     */
    List<Tag> findAllById( List<TagId> id );

}
