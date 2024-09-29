package com.aipixel.api.component.image;

import com.aipixel.api.common.datetime.LocalDateTimeFormatter;
import com.aipixel.api.component.category.CategoryMapper;
import com.aipixel.api.component.category.vo.CategoryId;
import com.aipixel.api.component.image.controller.dto.ImageDto;
import com.aipixel.api.component.image.controller.form.SaveImageForm;
import com.aipixel.api.component.image.enumeration.ValidContentType;
import com.aipixel.api.component.image.repository.ImageModel;
import com.aipixel.api.component.image.service.request.SaveImageServiceRequest;
import com.aipixel.api.component.image.vo.*;
import com.aipixel.api.component.tag.TagMapper;
import com.aipixel.api.component.tag.vo.TagId;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    public static SaveImageServiceRequest formToSaveImageServiceRequest( final SaveImageForm form ) {
        final MultipartFile image = form.getImage();
        final ValidContentType contentType = ValidContentType.of( image.getContentType() );
        final SaveImageServiceRequest.SaveImageServiceRequestBuilder builder = SaveImageServiceRequest
                .builder( ImageName.of( form.getName() ), image.getBytes(), contentType )
                .favorite( form.isFavorite() )
                .categories( form.getCategories().stream().map( CategoryId::of ).collect( Collectors.toSet() ))
                .tags( form.getTags().stream().map( TagId::of ).collect( Collectors.toSet() ));

        form.getDate().ifPresent( value -> builder.date(LocalDate.parse( value, LocalDateTimeFormatter.FULL_DATE_FORMATTER )));
        form.getDescription().ifPresent( value -> builder.description( ImageDescription.of( value )));
        form.getValoration().ifPresent( value -> builder.imageValoration( ImageValoration.of( value )));

        return builder.build();
    }


    public static ImageDto entityToImageDto( final Image image ) {
        final ImageDto.ImageDtoBuilder builder = ImageDto
                .builder( image.getId().toString(), image.getName().value(), image.getFileName().getNameValue() )
                .favorite( image.isFavorite() )
                .categories( image.getCategories().stream().map( CategoryMapper::entityToCategoryDto ).toList() )
                .tags( image.getTags().stream().map( TagMapper::entityToTagDto ).toList() );

        image.getDate().ifPresent( value -> builder.date( value.format( LocalDateTimeFormatter.FULL_DATE_FORMATTER )));
        image.getDescription().ifPresent( value -> builder.description( value.toString() ));
        image.getImageValoration().ifPresent( value -> builder.valoration( value.value() ));
        image.getCreatedAt().ifPresent( value -> builder.createdAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER )));
        image.getUpdatedAt().ifPresent( value -> builder.updatedAt( value.format( LocalDateTimeFormatter.FULL_DATE_TIME_FORMATTER )));

        return builder.build();
    }


    public static ImageModel entityToModel( final Image entity ) {
        final ImageModel.ImageModelBuilder builder = ImageModel
                .builder( entity.getId().toString(), entity.getName().value(), entity.getFileName().getNameValue() )
                .favorite( entity.isFavorite() )
                .categories( entity.getCategories().stream().map( CategoryMapper::entityToModel ).toList() )
                .tags( entity.getTags().stream().map( TagMapper::entityToModel ).toList() );

        entity.getDate().ifPresent( value -> builder.date( new Date( value.toEpochDay() )));
        entity.getDescription().ifPresent( value -> builder.description( value.toString() ));
        entity.getImageValoration().ifPresent( value -> builder.valoration( value.value() ));
        entity.getCreatedAt().ifPresent( value -> builder.createdAt( Timestamp.valueOf( value )));
        entity.getUpdatedAt().ifPresent( value -> builder.updatedAt( Timestamp.valueOf( value )));

        return builder.build();
    }


    public static Image modelToEntity( final ImageModel model ){
        final Image.ImageBuilder builder = Image
                .builder( ImageId.of( model.getId()), ImageName.of( model.getName()), ImageFileName.of( model.getFileName() ))
                .favorite( model.isFavorite() )
                .categories( model.getCategories().stream().map( CategoryMapper::modelToEntity ).collect( Collectors.toSet() ))
                .tags( model.getTags().stream().map( TagMapper::modelToEntity ).collect( Collectors.toSet() ));

        model.getDate().ifPresent( value -> builder.date( value.toLocalDate() ));
        model.getDescription().ifPresent( value -> builder.description( ImageDescription.of( value )));
        model.getValoration().ifPresent( value -> builder.imageValoration( ImageValoration.of( value )));

        return builder.build();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
