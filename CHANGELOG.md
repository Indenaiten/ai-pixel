# CHANGELOG

Todos los cambios notables en este proyecto se documentarán en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), y este proyecto se adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

<!-- ## [Unreleased] -->
<!-- ### ADDED ✔️-->
<!-- ### FIXED 🐛-->
<!-- ### CHANGED 🛠️-->
<!-- ### REMOVED 🗑️-->
<!-- ### SECURITY 🛡️-->
<!-- ### DEPRECATED 🛑-->

## UNRELEASED

### ADDED ✔️

#### `25/09/2024`

- Se ha creado el Objeto de Acceso a Datos (`DAO`) para la entidad `Image`.


- Se han creado los siguientes Objetos de Transferencia de Datos (`DTO`) para las entidades `Image`, `Tag` y `Category`:
    - `ImageDto`
    - `TagDto`
    - `CategoryDto`


- Se añadió el modelo `ImageModel` para representar la información de las imágenes en la Base de Datos.


- Se añadieron los siguientes `ValueObject` para la entidad `Image`:
    - `ImageId`
    - `ImageName`
    - `ImageFileName`
    - `ImageDescription`
    - `ImageValoration`


- Se añadió la entidad `Image` para representar las imágenes.


- Se añadió el modelo `TagModel` para representar la información de las etiquetas en la Base de Datos.


- Se añadieron los siguientes `ValueObject` para la entidad `Tag`:
    - `TagId`
    - `TagName`


- Se añadió la entidad `Tag` para representar las etiquetas de las imágenes.


- Se añadió el modelo `CategoryModel` para representar la información de las categorías en la Base de Datos.


- Se añadieron los siguientes `ValueObject` para la entidad `Category`:
    - `CategoryId`
    - `CategoryName`
    - `CategoryDescription`


- Se añadió la entidad `Category` para representar las categorías de las imágenes.


- Se añadió la interfaz `Entityable` para definir objetos que pueden ser convertidos a entidades de la aplicación.

 
- Se añadió la clase abstracta `ValueObject` para establecer la creación de objetos de valor.


- Se añadió la interfaz `Entity` para establecer la creación de Entidades.


- Se añadió el controlador génerico `AdviceController` para manejar las respuestas de la API REST cuando se producen
Excepciones en la aplicación.


- Se agregó el objeto `ApiResponse` para manejar las respuestas de la API REST.


- Se estableció la configuración de la base de datos en el archivo de propiedades de la aplicación.


- Se agregó el script SQL para la creación de la base de datos.

---