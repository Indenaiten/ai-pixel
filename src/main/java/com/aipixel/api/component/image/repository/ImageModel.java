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



/**
 * Clase que representa una imagen en la Base de Datos.
 * Se puede convertir en una entidad {@link Image} de la aplicación.
 *
 * @see Entityable
 */
@Entity
@Table( name = "images" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel implements Entityable<Image> {

    @Id
    @Column( name = "id", updatable = false, nullable = false )
    /** El identificador de la imagen. */
    private String id;

    @Column( name = "favorite", nullable = false )
    /** Indica si la imagen es favorita. */
    private boolean favorite;

    @Column( name = "date" )
    /** La fecha de la imagen. */
    private Date date;

    @Column( name = "name", nullable = false, length = ImageName.MAX_SIZE )
    /** El nombre de la imagen. */
    private String name;

    @Column( name = "file_name", nullable = false )
    /** El nombre del archivo de la imagen. */
    private String fileName;

    @Column( name = "description", length = ImageDescription.MAX_SIZE )
    /** La descripción de la imagen. */
    private String description;

    @Column( name = "valoration", length = ImageValoration.MAX_SIZE )
    /** La valoración de la imagen. */
    private Short valoration;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(
            name = "images_categories",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    /** Las categorías de la imagen. */
    private List<CategoryModel> categories;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(
            name = "images_tags",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "tag_id" )
    )
    /** Las etiquetas de la imagen. */
    private List<TagModel> tags;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false )
    /** La fecha y hora de creación de la imagen. */
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    /** La fecha y hora de la última actualización de la imagen. */
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor a partir de una entidad de tipo {@link Image}.
     *
     * @param image La entidad de tipo {@link Image}.
     */
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
