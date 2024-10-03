//CONSTANTS
const KEY_ID = 'id';
const KEY_FAV = 'favorite';
const KEY_DATE = 'date';
const KEY_TITLE = 'name';
const KEY_PROMPT = 'description';
const KEY_TAGS = 'tags';
const KEY_IMAGE = 'fileName';

const CLASS_NAME_MASONRY = 'masonry';
const CLASS_NAME_CARD = 'card';
const CLASS_NAME_FAV = 'fav';
const CLASS_NAME_NONE = 'none';
const CLASS_NAME_TITLE = 'title';
const CLASS_NAME_ID = 'id';
const CLASS_NAME_DESCRIPTION = 'description';
const CLASS_NAME_TAG = 'tag';
const CLASS_NAME_CLICKABLE = 'clickable';

const ID_SEARCH = 'search';
const ID_IMAGES_TOTAL = 'images-total';
const ID_MESSAGES_CONTAINER = 'messages';

let isLoading = false;
let imagesData = [];
let cardsLoaded = []


//Función para determinar cuántas columnas crear basado en el ancho de la pantalla
function getColumnCount() {
    const screenWidth = window.innerWidth;
    if (screenWidth >= 1500) {
        return 5; // 4 columnas para pantallas grandes
    } else if ( screenWidth >= 1200 ) {
        return 4;
    } else if ( screenWidth >= 850) {
        return 3;
    } else if ( screenWidth >= 600 ) {
        return 2;
    } else {
        return 1;
    }
}

//Función para crear las columnas en el DOM
function createColumns(columnCount) {
    const masonryContainer = document.querySelector( `.${CLASS_NAME_MASONRY}` );
    masonryContainer.innerHTML = ''; // Limpiar las columnas existentes
    for (let i = 0; i < columnCount; i++) {
        const column = document.createElement('div');
        column.classList.add('column');
        column.setAttribute('data-height', '0'); // Atributo para almacenar la altura total de la columna
        masonryContainer.appendChild(column);
    }
}

//Función para obtener la columna más baja
function getShortestColumn() {
    const columns = document.querySelectorAll(`.${CLASS_NAME_MASONRY} > .column` );
    let shortestColumn = columns[0];
    let minHeight = parseInt(shortestColumn.getAttribute('data-height'), 10);

    columns.forEach(column => {
        const columnHeight = parseInt(column.getAttribute('data-height'), 10);
        if (columnHeight < minHeight) {
            shortestColumn = column;
            minHeight = columnHeight;
        }
    });

    return shortestColumn;
}

//Función para obtener el último ID de las imágenes
function getLastId(){
    let lastId = null;
    if( imagesData.length > 0 ){
        lastId = imagesData[ imagesData.length - 1 ][ KEY_ID ];
    }
    return lastId;
}

//Función para cargar más imágenes cuando el usuario hace scroll
async function handleScroll() {
    // Verificamos si estamos cerca del final de la página
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight - (window.innerHeight * 1.5) && !isLoading ) {
        const data = await loadImagesData( ); // Cargar imágenes adicionales
        showData( data );
    }
}

//Función principal para inicializar el layout de columnas y cargar las imágenes
async function initMasonry( cleanData = true ) {
    const masonryContainer = document.querySelector(`.${CLASS_NAME_MASONRY}`);
    if( cleanData ) {
        imagesData = [];
        await loadImagesData();
    }
    const columnCount = getColumnCount(); // Obtener número de columnas
    masonryContainer.innerHTML = ''; // Limpiar las columnas existentes
    createColumns(columnCount); // Crear las columnas en el contenedor
    showData( imagesData );
}

//Función para cargar la información de las imágenes
async function loadImagesData() {
    let result = [];
    isLoading = true;
    const lastId = getLastId();
    await getAll( lastId ).then( res => res.json() ).then( res => {
        result = res.data;
        imagesData = imagesData.concat( result );
    });
    return result;
}

//Función para mostrar mensajes en la pantalla
function showData( data ){
    //Recorremos la información de las imágenes para crear las tarjetas
    data.forEach( async item => {
        //Creamos la tarjeta
        const card = createCard( item );

        //Recuperamos la imagen de la tarjeta
        const image = card.querySelector( `img` );

        //Cuando se carga la imagen, establecemos que se ha cargado la tarjeta, y cuando todas las tarjetas hayan sido
        //cargadas, las añadimos al DOM.
        image.addEventListener( 'load', function( event ) {
            //Comprobamos si la tarjeta ya ha sido cargada
            if( cardsLoaded.find( target => target.id === card.id ) ) return;
            image.loading = 'lazy';

            //Añadimos la tarjeta al array de tarjetas cargadas
            cardsLoaded.push( card );

            //Comprobamos si todas las tarjetas han sido cargadas
            if( cardsLoaded.length == data.length ){
                //Recorremos la información de las imágenes para añadir las tarjetas cargadas en el orden correcto
                data.forEach( imageData => {
                    //Buscamos la tarjeta en el array de tarjetas cargadas
                    const index = cardsLoaded.findIndex( target => target.id === imageData[ KEY_ID ] );
                    if( index < 0 ) return; //Si no la encontramos, no hacemos nada

                    //Recuperamos la tarjeta y la eliminamos del array de tarjetas cargadas
                    const cardLoaded = cardsLoaded[ index ];
                    cardsLoaded.splice( index, 1 );

                    //Añadimos la tarjeta a la columna más baja
                    const shortestColumn = getShortestColumn();
                    shortestColumn.appendChild( cardLoaded );

                    //Actualizamos la altura total de la columna
                    const cardHeight = cardLoaded.clientHeight;
                    const updatedHeight = parseInt( shortestColumn.getAttribute( 'data-height' ), 10 ) + cardHeight;
                    shortestColumn.setAttribute( 'data-height', updatedHeight.toString() );
                });
                isLoading = false;
            }
        });
    });
}

//Función para crear las tarjetas de las imágenes a partir de la información de las imágen
function createCard( data ){
    const id = data[ KEY_ID ];
    const fav = data[ KEY_FAV ];
    const date = data[ KEY_DATE ];
    const title = data[ KEY_TITLE ];
    const prompt = data[ KEY_PROMPT ];
    const tags = data[ KEY_TAGS ];
    const image = data[ KEY_IMAGE ];

    const cardElement = document.createElement( 'div' );
    cardElement.classList.add( CLASS_NAME_CARD );
    cardElement.id = id;

    const favElement = document.createElement( 'span' );
    favElement.classList.add( CLASS_NAME_FAV );
    if( !fav ) favElement.classList.add( CLASS_NAME_NONE );
    favElement.innerHTML = '★';

    const mainElement = document.createElement( 'main' );

    const imageElement = document.createElement( 'img' );
    imageElement.src = `http://localhost:8080/api/image/view/${id}`;
    imageElement.alt = title;

    const titleElement = document.createElement( 'h1' );
    titleElement.classList.add( CLASS_NAME_TITLE );
    titleElement.classList.add( CLASS_NAME_CLICKABLE );
    titleElement.textContent = title;
    titleElement.addEventListener( 'click', function(){ copyToClipBoard( title ) });

    const idElement = document.createElement( 'small' );
    idElement.classList.add( CLASS_NAME_ID );
    idElement.textContent = id;
    idElement.addEventListener( 'click', function(){ copyToClipBoard( id ) });

    const descriptionElement = document.createElement( 'p' );
    descriptionElement.classList.add( CLASS_NAME_DESCRIPTION );
    descriptionElement.classList.add( CLASS_NAME_CLICKABLE );
    descriptionElement.textContent = prompt;
    descriptionElement.addEventListener( 'click', function(){ copyToClipBoard( prompt ) });

    mainElement.appendChild( imageElement );
    mainElement.appendChild( titleElement );
    mainElement.appendChild( idElement );
    mainElement.appendChild( descriptionElement );

    const footerElement = document.createElement( 'footer' );

    tags.forEach( tagText => {
        const tag = document.createElement( 'span' );
        tag.classList.add( CLASS_NAME_TAG );
        tag.classList.add( CLASS_NAME_CLICKABLE );
        tag.textContent = tagText;
        tag.addEventListener( 'click', function(){
            copyToClipBoard( tagText );
            document.getElementById( ID_SEARCH ).value = tagText;
        });
        footerElement.appendChild( tag );
    });

    cardElement.appendChild( favElement );
    cardElement.appendChild( mainElement );
    cardElement.appendChild( footerElement );

    return cardElement;
}

//Función para copiar un texto al portapapeles
async function copyToClipBoard( text ){
    try {
        await navigator.clipboard.writeText( text );
        showMessage( 'success', `Texto copiado al portapapeles` );
    } catch (err) {
        showMessage( 'error', `Error al copiar el texto al portapapeles: ${err}` );
    }
    console.log( text );
}

//Función para recuperar la información de las todas las imágenes
function getAll( lastId = null ){
    let url = `http://localhost:8080/api/image/find/all`;
    if( lastId != null ){
        url = `${url}/${lastId}`;
    }
    return fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

//Main
window.addEventListener('load', () => {
    // Inicializar el layout de columnas cuando la página se carga
    initMasonry();

    // Vuelve a calcular las columnas cuando la ventana cambia de tamaño
    window.addEventListener('resize', () => { initMasonry( false );});

    // Detectar scroll para cargar más imágenes
    window.addEventListener('scroll', handleScroll);
});
