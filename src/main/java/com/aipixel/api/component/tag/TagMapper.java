package com.aipixel.api.component.tag;

import com.aipixel.api.common.datetime.LocalDateTimeFormatter;
import com.aipixel.api.component.tag.controller.dto.TagDto;
import com.aipixel.api.component.tag.repository.TagModel;
import com.aipixel.api.component.tag.vo.TagId;
import com.aipixel.api.component.tag.vo.TagName;

import java.sql.Timestamp;



/**
 * Clase que se encarga de mapear las objetos del componente de etiquetas a otros objetos.
 */
public class TagMapper {

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private TagMapper() {
        throw new UnsupportedOperationException( "Utility Class" );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| METHODS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static TagDto entityToTagDto( final Tag entity ) {
        final TagDto.TagDtoBuilder builder = TagDto.builder();

        entity.getId().ifPresent( value -> builder.id( value.value() ));
        entity.getName().ifPresent( value -> builder.name( value.toString() ));
        entity.getCreatedAt().ifPresent( value -> builder.createdAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER)));
        entity.getUpdatedAt().ifPresent( value -> builder.updatedAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER)));

        return builder.build();
    }


    public static TagModel entityToModel( final Tag entity ) {
        final TagModel.TagModelBuilder builder = TagModel.builder();

        entity.getId().ifPresent( value -> builder.id( value.value() ));
        entity.getName().ifPresent( value -> builder.name( value.toString() ));
        entity.getCreatedAt().ifPresent( value -> builder.createdAt( Timestamp.valueOf( value )));
        entity.getUpdatedAt().ifPresent( value -> builder.updatedAt( Timestamp.valueOf( value )));

        return builder.build();
    }


    public static Tag modelToEntity( final TagModel model ){
        final Tag.TagBuilder builder = Tag.builder();

        model.getId().ifPresent( value -> builder.id( TagId.of( value )));
        model.getName().ifPresent( value -> builder.name( TagName.of( value )));
        model.getCreatedAt().ifPresent( value -> builder.createdAt( value.toLocalDateTime() ));
        model.getUpdatedAt().ifPresent( value -> builder.updatedAt( value.toLocalDateTime() ));

        return builder.build();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
