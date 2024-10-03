//------------------------------------------------------------------------------------------------------------------- \\
//---| CONSTANTS |--------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

const HOST = 'http://localhost:8080';

const ENDPOINT_FIND_ALL = '/api/image/find/all';
const URL_FIND_ALL = `${HOST}${ENDPOINT_FIND_ALL}`;

const ENDPOINT_VIEW_IMAGE = '/api/image/view';
const URL_VIEW_IMAGE = `${HOST}${ENDPOINT_VIEW_IMAGE}`;

const ENDPOINT_COUNT_ALL = '/api/image/count/all';
const URL_COUNT_ALL = `${HOST}${ENDPOINT_COUNT_ALL}`;

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

// const ID_SEARCH = 'search';
const ID_IMAGES_TOTAL = 'images-total';
const ID_MESSAGES_CONTAINER = 'messages';



//------------------------------------------------------------------------------------------------------------------- \\
//---| VARIABLES |--------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

let total = 0;



//------------------------------------------------------------------------------------------------------------------- \\
//---| MAIN |-------------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

//Event listener to load the script
window.addEventListener('load', () => {
    //Create the Masonry object
    const masonry = new Masonry( createCard );

    //Fill the Masonry layout with the data to retrieve from the server
    findAll().then( response => masonry.fill( response.data ));

    //Set the callback handler to fill the Masonry layout with the data to retrieve from the server
    masonry.setCallbackHandlerScroll( () => {
        const data = masonry.getLastData();
        if( data !== null ){
            findAll( data[ KEY_ID ] ).then( response => masonry.fill( response.data ));
        }
    });
});



//------------------------------------------------------------------------------------------------------------------- \\
//---| FUNCTIONS |--------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

function showMessage( type, msg ){
    console.log( type, msg );

    //Create the message text element
    const messageTextElement = document.createElement( 'p' );
    messageTextElement.innerHTML = msg;

    //Create the message element and append the message text element
    const messageElement = document.createElement( 'div' );
    messageElement.classList.add( 'message', 'animate__animated', 'animate__bounceInRight', type );
    messageElement.appendChild( messageTextElement );

    //Get the messages container and append the message element
    const messagesContainer = document.getElementById( ID_MESSAGES_CONTAINER );
    messagesContainer.appendChild( messageElement );

    //Remove the message element after 3 seconds
    setTimeout( function(){
        messageElement.classList.remove( 'animate__bounceInRight' );
        messageElement.classList.add( 'animate__bounceOutRight' );
        messageElement.style.display = 'none';
    }, 3000 );
    setTimeout( function(){ messageElement.remove(); }, 4000);
}


/**
 * Function to copy a text to the clipboard.
 *
 * @param text The text to copy to the clipboard
 *
 * @returns {Promise<void>} The promise to copy the text to the clipboard
 */
async function copyToClipBoard( text ){
    try {
        await navigator.clipboard.writeText( text );
        showMessage( 'success', `Texto copiado al portapapeles` );
    } catch (err) {
        showMessage( 'error', `Error al copiar el texto al portapapeles: ${err}` );
    }
    console.log( text );
}


/**
 * Function to create a card element with the data provided.
 *
 * @param data The data to create the card element
 *
 * @returns {HTMLDivElement} The card element created
 */
function createCard( data ){
    //Initialize the data of the card
    const id = data[ KEY_ID ];
    const fav = data[ KEY_FAV ];
    const date = data[ KEY_DATE ];
    const title = data[ KEY_TITLE ];
    const prompt = data[ KEY_PROMPT ];
    const tags = data[ KEY_TAGS ];
    const image = data[ KEY_IMAGE ];

    //Create the fav element
    const favElement = document.createElement( 'span' );
    favElement.classList.add( CLASS_NAME_FAV );
    if( !fav ) favElement.classList.add( CLASS_NAME_NONE );
    favElement.innerHTML = '★';

    //Create the image element
    const imageElement = document.createElement( 'img' );
    imageElement.src = `${URL_VIEW_IMAGE}/${id}`;
    imageElement.alt = title;

    //Create the title element
    const titleElement = document.createElement( 'h1' );
    titleElement.classList.add( CLASS_NAME_TITLE );
    titleElement.classList.add( CLASS_NAME_CLICKABLE );
    titleElement.textContent = title;
    titleElement.addEventListener( 'click', () => copyToClipBoard( title ));

    //Create the id element
    const idElement = document.createElement( 'small' );
    idElement.classList.add( CLASS_NAME_ID );
    idElement.textContent = id;
    idElement.addEventListener( 'click', () => copyToClipBoard( id ));

    //Create the description element
    const descriptionElement = document.createElement( 'p' );
    descriptionElement.classList.add( CLASS_NAME_DESCRIPTION );
    descriptionElement.classList.add( CLASS_NAME_CLICKABLE );
    descriptionElement.textContent = prompt;
    descriptionElement.addEventListener( 'click', () => copyToClipBoard( prompt ));

    //Create the main element
    const mainElement = document.createElement( 'main' );
    mainElement.appendChild( imageElement );
    mainElement.appendChild( titleElement );
    mainElement.appendChild( idElement );
    mainElement.appendChild( descriptionElement );

    //Create the footer element
    const footerElement = document.createElement( 'footer' );

    //Create the tags elements
    tags.forEach( tagText => {
        const tag = document.createElement( 'span' );
        tag.classList.add( CLASS_NAME_TAG );
        tag.classList.add( CLASS_NAME_CLICKABLE );
        tag.textContent = tagText;
        tag.addEventListener( 'click', () => copyToClipBoard( tagText ));
        footerElement.appendChild( tag );
    });

    //Create the card element
    const cardElement = document.createElement( 'div' );
    cardElement.classList.add( CLASS_NAME_CARD );
    cardElement.id = id;
    cardElement.appendChild( favElement );
    cardElement.appendChild( mainElement );
    cardElement.appendChild( footerElement );

    //Return the card element
    return cardElement;
}


/**
 * Function to retrieve all the images from the server.
 *
 * @param lastId The last id to retrieve the images, if null retrieve all first images
 *
 * return {Promise<Response>} The promise to retrieve all the images from the server as a JSON object
 */
function findAll( lastId = null ){
    let url = URL_FIND_ALL;
    if( lastId != null ){
        url = `${url}/${lastId}`;
    }
    return fetch( url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then( response => response.json() );
}



/**
 * Function to update the total of images in the server.
 */
function updateTotalImages(){
    fetch( URL_COUNT_ALL )
        .then( response => response.json() )
        .then( response => {
            total = response.data;
            let keyWord = 'imágenes';
            if( total === 1 ) keyWord = 'imagen'
            const message = `${total} ${keyWord}`;
            document.getElementById( ID_IMAGES_TOTAL ).innerHTML = message;
            console.log( message )
        })
}

//------------------------------------------------------------------------------------------------------------------- \\