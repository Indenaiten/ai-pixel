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

let totalImages = 0;
let imagesData = [];

let imageCount = 0; // Contador de imágenes cargadas
let isLoading = false; // Bandera para evitar cargas múltiples

window.addEventListener('load', () => {
    // Función para cargar imágenes
    function loadImages() {
        let count = 15;
        let imagesLoaded = 0;
        getAll().then( res => res.json() ).then( res => {
            const masonry = document.querySelector(`.${CLASS_NAME_MASONRY}` );
                count++;
                res.data.forEach( data => {
                const card = createCard( data );
                masonry.appendChild(card);

                // card.onload = () => {
                //     adjustCardItem(card);
                //     imagesLoaded++;
                //     if (imagesLoaded === count) {
                //         isLoading = false;
                //     }
                // };

                imageCount++;
            });
        });
    }

    // Función para ajustar el span de las filas


    // Función para detectar el scroll al final de la página
    function onScroll() {
        if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 100 && !isLoading) {
            isLoading = true;
            loadImages();
        }
    }

    // Añadir el event listener para el scroll
    window.addEventListener('scroll', onScroll);

    // Cargar imágenes iniciales
    loadImages();
});

function adjustCardItem(item) {
    const masonry = document.querySelector(`.${CLASS_NAME_MASONRY}` );
    const rowHeight = parseInt(window.getComputedStyle(masonry).getPropertyValue('grid-auto-rows'));
    const rowGap = parseInt(window.getComputedStyle(masonry).getPropertyValue('grid-gap'));
    const contentHeight = item.querySelector('img').getBoundingClientRect().height;
    // const contentHeight = item.getBoundingClientRect().height;

    const rowSpan = Math.ceil((contentHeight + rowGap) / (rowHeight + rowGap));

    item.style.gridRowEnd = `span ${rowSpan}`;
}

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

    const favElement = document.createElement( 'span' );
    favElement.classList.add( CLASS_NAME_FAV );
    if( !fav ) favElement.classList.add( CLASS_NAME_NONE );
    favElement.innerHTML = '★';

    const mainElement = document.createElement( 'main' );

    const imageElement = document.createElement( 'img' );
    imageElement.src = `http://localhost:8080/api/image/view/${id}`;
    imageElement.alt = title;
    imageElement.loading = 'lazy';
    imageElement.onload = () => {
        adjustCardItem(cardElement);
    };

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
            show( search( tagText ) );
            document.getElementById( ID_SEARCH ).value = tagText;
        });
        footerElement.appendChild( tag );
    });

    cardElement.appendChild( favElement );
    cardElement.appendChild( mainElement );
    cardElement.appendChild( footerElement );

    return cardElement;
}


async function copyToClipBoard( text ){
    try {
        await navigator.clipboard.writeText( text );
        showMessage( 'success', `Texto copiado al portapapeles` );
    } catch (err) {
        showMessage( 'error', `Error al copiar el texto al portapapeles: ${err}` );
    }
    console.log( text );
}


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

/*

//MAIN
// window.addEventListener( 'resize', async function(){
//     console.log( window.innerWidth );
// });
window.addEventListener( 'load', async function(){
    updateTotalImages();
    const searchElement = document.getElementById( ID_SEARCH );
    searchElement.addEventListener( 'input', function( event ){
        if( event.target.value != null ){
            let textSearch = event.target.value.toLowerCase();
            show( search( textSearch, imagesData ))
        }
    });

    searchElement.addEventListener( 'click', async function( event ){
        const value = event.target.value;
        event.target.value = '';
        if( value !== '' ){
            getAll().then( res => show( res.data ))
        }
    });

    window.addEventListener( 'scroll', function( event ){

        if( window.scrollY + window.innerHeight >= document.documentElement.scrollHeight ){
            updateTotalImages();
            if( imagesData.length > 0 && imagesData.length < totalImages ) {
                const id = imagesData[imagesData.length - 1][KEY_ID];
                getAll(id).then(res => {
                    res.data.forEach(item => imagesData.push(item));
                    show(res.data, false);
                });
            }
        }
    });

    getAll().then( res => {
        show( res.data );
        imagesData = res.data;
    })
});


function adjustGridItem(item) {
    const masonry = document.querySelector('.${CLASS_NAME_MASONRY}');
    const rowHeight = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-auto-rows'));
    const rowGap = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-gap'));
    const contentHeight = item.querySelector('img').getBoundingClientRect().height;

    const rowSpan = Math.ceil((contentHeight + rowGap) / (rowHeight + rowGap));

    item.style.gridRowEnd = `span ${rowSpan}`;
}





//FUNCTIONS
function show( data, remove = true ){
    //updateTotalImagesFromSearch( data );
    const masonry = document.querySelector( `.${CLASS_NAME_MASONRY}` );
    if( remove ) masonry.innerHTML = '';

    data.forEach( item => {
        const id = item[ KEY_ID ];
        const fav = item[ KEY_FAV ];
        const date = item[ KEY_DATE ];
        const title = item[ KEY_TITLE ];
        const prompt = item[ KEY_PROMPT ];
        const tags = item[ KEY_TAGS ];
        const image = item[ KEY_IMAGE ];

        const cardElement = document.createElement( 'div' );
        cardElement.classList.add( CLASS_NAME_CARD );

        const favElement = document.createElement( 'span' );
        favElement.classList.add( CLASS_NAME_FAV );
        if( !fav ) favElement.classList.add( CLASS_NAME_NONE );
        favElement.innerHTML = '★';

        const mainElement = document.createElement( 'main' );

        const imageElement = document.createElement( 'img' );
        imageElement.src = `http://localhost:8080/api/image/view/${id}`;
        imageElement.alt = title;
        imageElement.loading = 'lazy';
        imageElement.onload = () => {
            adjustGridItem(cardElement);
        };

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
                show( search( tagText ) );
                document.getElementById( ID_SEARCH ).value = tagText;
            });
            footerElement.appendChild( tag );
        });

        cardElement.appendChild( favElement );
        cardElement.appendChild( mainElement );
        cardElement.appendChild( footerElement );

        masonry.append( cardElement );
    });
}



function search( _search, _data ){
    console.log( _search );
    const search = _search.toLowerCase();
    return _data.filter( image => {
        console.log( image[ KEY_PROMPT ])
        return ( image[ KEY_ID ] != null && image[ KEY_ID ] === _search ) ||
            ( image[ KEY_TITLE ] != null && image[ KEY_TITLE ].toLowerCase().includes( search )) ||
            ( image[ KEY_PROMPT ] != null && image[ KEY_PROMPT ].toLowerCase().includes( search )) ||
            ( image[ KEY_TAGS ] != null && image[ KEY_TAGS ].find( tag => tag.toLowerCase() === search ));
    })
}



function showMessage( type, msg ){
    const messagesContainer = document.getElementById( ID_MESSAGES_CONTAINER );

    const divElement = document.createElement( 'div' );
    divElement.classList.add( 'message', 'animate__animated', 'animate__bounceInRight', type );

    const textElement = document.createElement( 'p' );
    textElement.innerHTML = msg;

    divElement.appendChild( textElement );
    messagesContainer.appendChild( divElement );

    console.log( type, msg );

    setTimeout( function(){
        divElement.classList.remove( 'animate__bounceInRight' );
        divElement.classList.add( 'animate__bounceOutRight' );
        divElement.style.display = 'none';
    }, 3000 );

    setTimeout( function(){ divElement.remove(); }, 4000);
}



function getIdFromImage( image ){
    let result = null;
    const imageFileName = image[ KEY_IMAGE ];
    const parts = imageFileName.split( '.' );
    if( parts.length > 0 ) result = parts[ 0 ];
    return result;
}







function updateTotalImagesFromSearch( data ){
    const totalImages = data.length;
    let keyWord = 'imágenes';
    if( totalImages === 1 ) keyWord = 'imagen'
    const message = `${totalImages} ${keyWord}`;
    document.getElementById( ID_IMAGES_TOTAL ).innerHTML = message;
    console.log( message )
}



function updateTotalImages(){
    fetch( `http://localhost:8080/api/image/count/all` )
        .then( response => response.json() )
        .then( response => {
            totalImages = response.data;
            let keyWord = 'imágenes';
            if( totalImages === 1 ) keyWord = 'imagen'
            const message = `${totalImages} ${keyWord}`;
            document.getElementById( ID_IMAGES_TOTAL ).innerHTML = message;
            console.log( message )
        })
}

*/