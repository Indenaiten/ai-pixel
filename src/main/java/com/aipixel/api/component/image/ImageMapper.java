package com.aipixel.api.component.image;

import com.aipixel.api.common.datetime.LocalDateTimeFormatter;
import com.aipixel.api.component.category.CategoryMapper;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.image.controller.dto.ImageDto;
import com.aipixel.api.component.image.controller.form.SaveImageForm;
import com.aipixel.api.component.image.repository.ImageModel;
import com.aipixel.api.component.image.service.request.SaveImageRequest;
import com.aipixel.api.component.image.vo.*;
import com.aipixel.api.component.tag.TagMapper;
import com.aipixel.api.component.tag.vo.TagId;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;



/**
 * Clase que se encarga de mapear las objetos del componente de imágenes a otros objetos.
 */
public class ImageMapper {

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private ImageMapper() {
        throw new UnsupportedOperationException( "Utility Class" );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| METHODS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @SneakyThrows
    public static SaveImageRequest formToSaveImageRequest( final SaveImageForm form ) {
        final SaveImageRequest.SaveImageRequestBuilder builder = SaveImageRequest.builder();

        builder.favorite( form.isFavorite() );
        builder.categories( form.getCategories().stream().map( CategoryId::of ).collect( Collectors.toSet() ));
        builder.tags( form.getTags().stream().map( TagId::of ).collect( Collectors.toSet() ));

        form.getDate().ifPresent( value -> builder.date(LocalDate.parse( value, LocalDateTimeFormatter.FULL_DATE_FORMATTER )));
        form.getName().ifPresent( value -> builder.name( ImageName.of( value )));
        form.getDescription().ifPresent( value -> builder.description( ImageDescription.of( value )));
        form.getValoration().ifPresent( value -> builder.imageValoration( ImageValoration.of( value )));

        if( form.getImage().isPresent() ){
            final MultipartFile image = form.getImage().get();
            builder.fileName( image.getOriginalFilename() );
            builder.fileContentType( image.getContentType() );
            builder.fileContent( image.getBytes() );
        }

        return builder.build();
    }


    public static ImageDto entityToImageDto( final Image image ) {
        final ImageDto.ImageDtoBuilder builder = ImageDto.builder();

        builder.favorite( image.isFavorite() );
        builder.categories( image.getCategories().stream().map( CategoryMapper::entityToCategoryDto ).collect( Collectors.toList() ));
        builder.tags( image.getTags().stream().map( TagMapper::entityToTagDto ).collect( Collectors.toList() ));

        image.getId().ifPresent( value -> builder.id( value.toString() ));
        image.getDate().ifPresent( value -> builder.date( value.format( LocalDateTimeFormatter.FULL_DATE_FORMATTER )));
        image.getName().ifPresent( value -> builder.name( value.toString() ));
        image.getFileName().ifPresent( value -> builder.fileName( value.getNameValue() ));
        image.getDescription().ifPresent( value -> builder.description( value.toString() ));
        image.getImageValoration().ifPresent( value -> builder.valoration( value.value() ));
        image.getCreatedAt().ifPresent( value -> builder.createdAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER )));
        image.getUpdatedAt().ifPresent( value -> builder.updatedAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER )));

        return builder.build();
    }


    public static ImageModel entityToModel( final Image entity ) {
        final ImageModel.ImageModelBuilder builder = ImageModel.builder();

        builder.favorite( entity.isFavorite() );
        builder.categories( entity.getCategories().stream().map( CategoryMapper::entityToModel ).collect( Collectors.toList() ));
        builder.tags( entity.getTags().stream().map( TagMapper::entityToModel ).collect( Collectors.toList() ));

        entity.getId().ifPresent( value -> builder.id( value.toString() ));
        entity.getDate().ifPresent( value -> builder.date( new Date( value.toEpochDay() )));
        entity.getName().ifPresent( value -> builder.name( value.toString() ));
        entity.getFileName().ifPresent( value -> builder.fileName( value.getNameValue() ));
        entity.getDescription().ifPresent( value -> builder.description( value.toString() ));
        entity.getImageValoration().ifPresent( value -> builder.valoration( value.value() ));
        entity.getCreatedAt().ifPresent( value -> builder.createdAt( Timestamp.valueOf( value )));
        entity.getUpdatedAt().ifPresent( value -> builder.updatedAt( Timestamp.valueOf( value )));

        return builder.build();
    }


    public static Image modelToEntity( final ImageModel model ){
        final Image.ImageBuilder builder = Image.builder();

        builder.favorite( model.isFavorite() );
        builder.categories( model.getCategories().stream().map( CategoryMapper::modelToEntity ).collect( Collectors.toSet() ));
        builder.tags( model.getTags().stream().map( TagMapper::modelToEntity ).collect( Collectors.toSet() ));

        model.getId().ifPresent( value -> builder.id( ImageId.of( value )));
        model.getDate().ifPresent( value -> builder.date( value.toLocalDate() ));
        model.getName().ifPresent( value -> builder.name( ImageName.of( value )));
        model.getFileName().ifPresent( value -> builder.fileName( ImageFileName.of( value )));
        model.getDescription().ifPresent( value -> builder.description( ImageDescription.of( value )));
        model.getValoration().ifPresent( value -> builder.imageValoration( ImageValoration.of( value )));

        return builder.build();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
