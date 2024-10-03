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


class Masonry {
    DEFAULE_ATTR_COLUMN_HEIGHT = 'data-height';
    DEFAULT_CLASS_NAME_MASONRY = 'masonry';
    DEFAULT_CLASS_NAME_MASONRY_COLUMN = 'masonry-column';
    DEFAULT_SELECTOR_IMAGE = 'img';
    DEFAULT_KEY_ID = 'id';
    DEFAULT_COLUMN_COUNT_MAP = new Map([ [0, 1], [600, 2], [850, 3], [1200, 4], [1500, 5] ]);

    constructor( findAllMethodCallback, createElementMethodCallback, options = {} ){
        this.classNameMasonry = options['class-name-masonry'] || this.DEFAULT_CLASS_NAME_MASONRY;
        this.classNameMasonryColumn = options['class-name-masonry'] || this.DEFAULT_CLASS_NAME_MASONRY_COLUMN;
        this.attrColumnHeight = options['attr-column-height'] || this.DEFAULE_ATTR_COLUMN_HEIGHT;
        this.selectorImage = options['selector-image'] || this.DEFAULT_SELECTOR_IMAGE;
        this.keyId = options['key-id'] || this.DEFAULT_KEY_ID;
        this.columnCountMap = options['column-count-map'] || this.DEFAULT_COLUMN_COUNT_MAP;
        this.columnCountMap = new Map([...this.columnCountMap.entries()].sort((a, b) => b[0] - a[0]));
        this.masonryContainer = document.querySelector( `.${this.selectorMasonry}` );
        this.findAllMethodCallback = findAllMethodCallback;
        this.createElementMethodCallback = createElementMethodCallback;
        this.data = [];
        this.elementsLoaded = [];
        this.isLoading = false;

        // Vuelve a calcular las columnas cuando la ventana cambia de tamaño
        window.addEventListener( 'resize', () => this.fillMasonry.bind( this ));

        // Detectar scroll para cargar más imágenes
        window.addEventListener( 'scroll', this.handleScroll.bind( this ));
    }

    /**
     * Función para inicializar los valores de la clase
     */
    initValues(){
        this.data = [];
        this.elementsLoaded = [];
        this.loading = false;
    }


    /**
     * Función para llenar el layout de masonry con la información proporcionada
     *
     * @param data La información a mostrar en el layout. Por defecto es null y se cargará la información desde el servidor
     */
    async fillMasonry( data = null ){
        if( data === null ){
            this.initValues();
            await this.load();
            data = [];
        }
        this.data = this.data.concat( data );
        this.masonryContainer.innerHTML = ''; // Limpiar las columnas existentes
        this.createColumns(); // Crear las columnas en el contenedor
        data.length > 0 ? this.show( data ) : this.show();
    }


    /**
     * Función para determinar cuántas columnas crear basado en el ancho de la pantalla
     *
     * @returns {number} El número de columnas a crear
     */
    getColumnCount() {
        let result = 0;
        const screenWidth = window.innerWidth;

        for( const [ key, value ] of this.columnCountMap ){
            if( screenWidth >= key ) {
                result = value;
                break;
            }
        }

        return result;
    }


    /**
     * Función para crear las columnas en el DOM
     *
     * @param columnCount {number} El número de columnas a crear
     */
    createColumns( columnCount = this.getColumnCount() ) {
        this.masonryContainer.innerHTML = ''; // Limpiar las columnas existentes
        for( let i = 0; i < columnCount; i++ ){
            const column = document.createElement( 'div' );
            column.classList.add( this.classNameMasonryColumn );
            column.setAttribute( this.attrColumnHeight, '0' ); // Atributo para almacenar la altura total de la columna
            this.masonryContainer.appendChild( column );
        }
    }


    /**
     * Función para obtener la columna más pequeña del layout
     *
     * @returns {Element} La columna más pequeña del layout
     */
    getShortestColumn() {
        const columns = this.getColumnsElements();
        let shortestColumn = columns[0];
        let minHeight = this.parseInt( shortestColumn.getAttribute( this.attrColumnHeight ));

        columns.forEach( column => {
            const columnHeight = this.parseInt( column.getAttribute( this.attrColumnHeight ));
            if( columnHeight < minHeight ) {
                shortestColumn = column;
                minHeight = columnHeight;
            }
        });

        return shortestColumn;
    }


    /**
     * Función para cargar la información de los elementos
     *
     * @param callback {function} La función de callback para cargar la información de los elementos
     *
     * @returns {Promise<void>}
     */
    async load( callback = this.findAllMethodCallback ){
        this.loading = true;
        const result = await callback( this.getLastId() )
        this.data = this.data.concat( result );
        return result;
    }


    /**
     * Función para mostrar la información en el layout de masonry
     *
     * @param data La información a mostrar en el layout. Por defecto es la información de la clase
     */
    show( data = this.data ){
        //Recorremos la información para crear los elementos
        data.forEach( async itemData => {

            //Creamos el elemento
            const element = this.createElementMethodCallback( itemData );

            //Recuperamos la imagen del elemento
            const image = element.querySelector( this.selectorImage );

            //Cuando se carga la imagen, establecemos que se ha cargado el elemento, y cuando todos los elementos hayan
            // sido cargados, los añadimos al DOM.
            image.addEventListener( 'load', ( event ) => {
                //Comprobamos si el elemento ya ha sido cargado
                if( this.elementsLoaded.find( target => target.id === element.id ) ) return;

                //Establecemos que la imagen se cargue de forma diferida
                image.loading = 'lazy';

                //Añadimos el elemento al array de elementos cargados
                this.elementsLoaded.push( element );

                //Comprobamos si todos los elementos han sido cargados
                if( this.elementsLoaded.length == data.length ){

                    //Recorremos la información de los elementos para añadir los elementos cargados en el orden correcto
                    data.forEach( elementData => {
                        //Buscamos la tarjeta en el array de tarjetas cargadas
                        const index = this.elementsLoaded.findIndex( target => target.id === elementData[ this.dataKeyId ] );
                        if( index < 0 ) return; //Si no lo encontramos, no hacemos nada

                        //Recuperamos el elemento y lo eliminamos del array de elementos cargados
                        const elementLoaded = this.elementsLoaded[ index ];
                        this.elementsLoaded.splice( index, 1 );

                        //Añadimos el elemento a la columna más baja
                        const shortestColumn = this.getShortestColumn();
                        shortestColumn.appendChild( elementLoaded );

                        //Actualizamos la altura total de la columna con la altura del elemento
                        const elementHeight = elementLoaded.clientHeight;
                        const updatedHeight = this.parseInt( shortestColumn.getAttribute( this.attrColumnHeight )) + elementHeight;
                        shortestColumn.setAttribute( this.attrColumnHeight, updatedHeight.toString() );
                    });

                    //Establecemos que ya no se está cargando la información
                    this.loading = false;
                }
            });
        });
    }

    /**
     * Función para manejar el scroll de la página
     */
    async handleScroll() {
        // Verificamos si estamos cerca del final de la página
        if( window.innerHeight + window.scrollY >= document.body.offsetHeight - (window.innerHeight * 1.5) && !this.loading ) {
            const data = await this.load();
            this.show( data );
        }
    }


    /**
     * Función para obtener los elementos de las columnas
     *
     * @returns {NodeListOf<Element>} Los elementos de las columnas
     */
    getColumnsElements() {
        return document.querySelectorAll(`.${this.selectorMasonry} > .${this.classNameMasonryColumn}` );
    }


    /**
     * Función para parsear un valor a entero con base 10
     *
     * @param value {string} El valor a parsear a entero
     * @param base {number} La base para el parseo a entero. Por defecto es 10
     *
     * @returns {number} El valor parseado a entero
     */
    parseInt( value, base = 10 ) {
        return parseInt( value, 10 );
    }


    /**
     * Función para obtener la información del último elemento recuperado
     *
     * @returns {null} La información del último elemento recuperado
     */
    getLastId(){
        let result = null;
        if( this.data.length > 0 ){
            result = this.data[ this.data.length - 1 ][ this.dataKeyId ];
        }
        return result;
    }
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
    })
    .then( response => response.json() )
    .then( response => response.data );
}

//Main
window.addEventListener('load', () => {
    const masonry = new Masonry( getAll, createCard, {} );
    masonry.fillMasonry()
});
