package com.aipixel.api.component.category;

import com.aipixel.api.common.datetime.LocalDateTimeFormatter;
import com.aipixel.api.component.category.controller.dto.CategoryDto;
import com.aipixel.api.component.category.repository.CategoryModel;
import com.aipixel.api.component.category.vo.CategoryDescription;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.category.vo.CategoryName;

import java.sql.Timestamp;



/**
 * Clase que se encarga de mapear las objetos del componente de categorías a otros objetos.
 */
public class CategoryMapper {

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private CategoryMapper() {
        throw new UnsupportedOperationException( "Utility Class" );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| METHODS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static CategoryDto entityToCategoryDto( final Category entity ) {
        final CategoryDto.CategoryDtoBuilder builder = CategoryDto
                .builder( entity.getId().value(), entity.getName().value() );

        entity.getDescription().ifPresent( value -> builder.description( value.toString() ));
        entity.getCreatedAt().ifPresent( value -> builder.createdAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER )));
        entity.getUpdatedAt().ifPresent( value -> builder.updatedAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER )));

        return builder.build();
    }


    public static CategoryModel entityToModel( final Category entity ) {
        final CategoryModel.CategoryModelBuilder builder = CategoryModel
                .builder( entity.getId().value(), entity.getName().value() );

        entity.getDescription().ifPresent( value -> builder.description( value.toString() ));
        entity.getCreatedAt().ifPresent( value -> builder.createdAt( Timestamp.valueOf( value )));
        entity.getUpdatedAt().ifPresent( value -> builder.updatedAt( Timestamp.valueOf( value )));

        return builder.build();
    }


    public static Category modelToEntity( final CategoryModel model ){
        final Category.CategoryBuilder builder = Category
                .builder( CategoryId.of( model.getId()), CategoryName.of( model.getName() ));

        model.getDescription().ifPresent( value -> builder.description( CategoryDescription.of( value )));
        model.getCreatedAt().ifPresent( value -> builder.createdAt( value.toLocalDateTime() ));
        model.getUpdatedAt().ifPresent( value -> builder.updatedAt( value.toLocalDateTime() ));

        return builder.build();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
