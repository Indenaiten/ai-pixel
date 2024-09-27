# AI Pixel

## Índice

- [Descripción](#descripción)
- [Requisitos](#requisitos)
- [Despliegue](#despliegue)
  - [Desplegar con el IDE](#Desplegar-con-el-IDE)
  - [Desplegar en Local](#Desplegar-en-Local)
  - [Desplegar con Docker](#Desplegar-con-Docker)
  - [Detener el Docker-Compose](#Detener-el-Docker-Compose)
- [Contribución](#contribución)
- [Versionado](#versionado)
- [Desarrolladores](#desarrolladores)

## Descripción

AI Pixel es una API para gestionar la persistencia de imágenes, sobre todo realizadas con Inteligencia Artificial, y poder almacenar información como el prompt utilizado para generarla.

También nos permitirá crear y marcar las imágenes con etiquetas y categorías, además de establecer imágenes como favoritas e incluso valorarlas.

## Requisitos

> **Java:** `21`  
> **MAVEN:** `3.9.5`

## Despliegue

### Desplegar con el IDE

- Levantamos los servicios con Docker (database):

    ```shell
    docker compose up
    ```

- Levantamos la aplicación con el IDE arrancando la clase `com.aipixel.api.Application` con el argumento `--spring.profiles.active=local`.

### Desplegar en Local

- Levantamos los servicios con Docker (database):

    ```shell
    docker compose up
    ```

- Compilamos la aplicación con el siguiente comando de MAVEN:

    ```shell
    mvn clean install
    ```

- Ejecutamos con Java el JAR `target/ai-pixel-v1.0.0-SNAPSHOT.jar` que se ha generado con el comando anterior con el siguiente comando:

    ```shell
    java -jar target/ai-pixel-v1.0.0-SNAPSHOT.jar --spring.profiles.active=local
    ```

### Desplegar con Docker

Levantamos el fichero `docker-compose.yaml` con el perfil `app` con el siguiente comando:

```shell
docker compose --profile app up
```
  
### Detener el Docker-Compose

Para parar la ejecución del fichero `docker-compose.yaml` lanzarémos el siguiente comando:

```shell
docker compose down
```
```shell
docker compose --profile app down
```

Y si queremos pararla y además eliminar los volúmenes, le añadiremos el argumento `-v`

```shell
docker compose down -v
```


## Contribución

### Pull Requests

Para añadir nuevos cambios al proyecto se deberán realizar en una nueva rama creada desde la rama `develop`. Cuando los cambios esten listos, se realizará un rebase con la rama `develop` para resolver conflictos si los hubiese y se realizará una `Pull Request` de la rama con los nuevos cambios a la rama `develop`.  
La fusión de nuevos cambios se hará con la estrategia `Merge`.

### Convención de Commits

Para garantizar un historial de commits coherente y fácil de entender, seguimos la convención de los [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/). Consulta nuestra [guía de formato de commit](COMMIT_CONVENTION.md) para obtener más detalles.

## Versionado

Para el control de versiones, este proyecto sigue la convención de [Versionado Semántico (SemVer)](https://semver.org/). Los cambios en el código se registran con una versión específica que sigue el formato `MAJOR`.`MINOR`.`PATCH`, donde:

- `MAJOR` versión cuando se realizan cambios incompatibles.
- `MINOR` versión cuando se añade funcionalidad de manera compatible.
- `PATCH` versión cuando se realizan correcciones de errores compatibles.

## Desarrolladores

- Ángel Herce Soto - [GitHub](https://github.com/indenaiten)