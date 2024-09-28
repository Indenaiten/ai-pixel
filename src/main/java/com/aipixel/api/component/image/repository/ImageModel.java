package com.aipixel.api.component.image.repository;

import com.aipixel.api.component.category.repository.CategoryModel;
import com.aipixel.api.component.image.vo.ImageDescription;
import com.aipixel.api.component.image.vo.ImageName;
import com.aipixel.api.component.image.vo.ImageValoration;
import com.aipixel.api.component.tag.repository.TagModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


/**
 * Clase que representa una imagen en la Base de Datos.
 */
@Entity
@Table( name = "images" )
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {

    @Id
    @Column( name = "id", updatable = false, nullable = false )
    private String id;
    public static final String FIELD_ID = "id";


    @Column( name = "favorite", nullable = false )
    @Getter( AccessLevel.NONE )
    private Boolean favorite;
    public static final String FIELD_FAVORITE = "favorite";


    @Column( name = "date" )
    private Date date;
    public static final String FIELD_DATE = "date";


    @Column( name = "name", nullable = false, length = ImageName.MAX_SIZE )
    private String name;
    public static final String FIELD_NAME = "name";


    @Column( name = "file_name", nullable = false )
    private String fileName;
    public static final String FIELD_FILE_NAME = "fileName";


    @Column( name = "description", length = ImageDescription.MAX_SIZE )
    private String description;
    public static final String FIELD_DESCRIPTION = "description";


    @Column( name = "valoration", length = ImageValoration.MAX_SIZE )
    private Short valoration;
    public static final String FIELD_VALORATION = "valoration";


    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable(
            name = "images_categories",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    private List<CategoryModel> categories;
    public static final String FIELD_CATEGORIES = "categories";


    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable(
            name = "images_tags",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "tag_id" )
    )
    private List<TagModel> tags;
    public static final String FIELD_TAGS = "tags";


    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private Timestamp createdAt;
    public static final String FIELD_CREATED_AT = "createdAt";


    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;
    public static final String FIELD_UPDATED_AT = "updatedAt";



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public boolean isFavorite(){
        return this.favorite != null && this.favorite;
    }

    public Optional<Date> getDate(){
        return Optional.ofNullable( this.date );
    }

    public Optional<String> getDescription(){
        return Optional.ofNullable( this.description );
    }

    public Optional<Short> getValoration(){
        return Optional.ofNullable( this.valoration );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static ImageModelBuilder builder( final String id, final String name, final String fileName ){
        return new ImageModelBuilder().id( id ).name( name ).fileName( fileName );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
