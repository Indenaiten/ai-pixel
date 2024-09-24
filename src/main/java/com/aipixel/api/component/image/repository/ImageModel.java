package com.aipixel.api.component.image.repository;

import com.aipixel.api.common.entity.Entityable;
import com.aipixel.api.component.category.repository.CategoryModel;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.vo.ImageDescription;
import com.aipixel.api.component.image.vo.ImageName;
import com.aipixel.api.component.image.vo.ImageValoration;
import com.aipixel.api.component.tag.repository.TagModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table( name = "images" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel implements Entityable<Image> {

    @Id
    @Column( name = "id", updatable = false, nullable = false )
    private String id;

    @Column( name = "favorite", nullable = false )
    private boolean favorite;

    @Column( name = "date" )
    private Date date;

    @Column( name = "name", nullable = false, length = ImageName.MAX_SIZE )
    private String name;

    @Column( name = "file_name", nullable = false )
    private String fileName;

    @Column( name = "description", length = ImageDescription.MAX_SIZE )
    private String description;

    @Column( name = "valoration", length = ImageValoration.MAX_SIZE )
    private Short valoration;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(
            name = "images_categories",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    private List<CategoryModel> categories;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(
            name = "images_tags",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "tag_id" )
    )
    private List<TagModel> tags;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false )
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageModel( final Image image ){
        this.id = image.getId().toString();
        this.favorite = image.getFavorite();
        image.getDate().ifPresent( date -> this.date = new Date( date.toEpochDay() ) );
        this.name = image.getName().value();
        this.fileName = image.getFileName().getName();
        image.getDescription().ifPresent( description -> this.description = description.value() );
        image.getImageValoration().ifPresent( valoration -> this.valoration = valoration.value() );
        if( !image.getCategories().isEmpty() ) this.categories = image.getCategories().stream().map( CategoryModel::new ).collect( Collectors.toList() );
        if( !image.getTags().isEmpty() ) this.tags = image.getTags().stream().map( TagModel::new ).collect( Collectors.toList() );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public Image toEntity() {
        final LocalDate date = this.date != null ? this.date.toLocalDate() : null;
        return Image.of(
                this.id,
                this.favorite,
                date,
                this.name,
                this.fileName,
                this.description,
                this.valoration,
                this.categories.stream().map( CategoryModel::toEntity ).collect(Collectors.toList()),
                this.tags.stream().map( TagModel::toEntity ).collect(Collectors.toList()),
                this.createdAt.toLocalDateTime(),
                this.updatedAt.toLocalDateTime()
        );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
