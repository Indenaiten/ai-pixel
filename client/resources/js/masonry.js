/**
 * class Masonry to manage the Masonry layout
 */
class Masonry {

//---| CONSTANTS |--------------------------------------------------------------------------------------------------- \\

    DEFAULT_SELECTOR_MASONRY = '.masonry';
    DEFAULT_SELECTOR_IMAGE = 'img';
    DEFAULT_DATA_KEY_ID = 'id';
    DEFAULE_ATTR_COLUMN_HEIGHT = 'data-height';
    DEFAULT_COLUMN_COUNT_MAP = new Map([[0, 1], [600, 2], [850, 3], [1200, 4], [1500, 5]]);

    CLASS_NAME_MASONRY_COLUMN = 'masonry-column';


//------------------------------------------------------------------------------------------------------------------- \\
//---| CONSTRUCTOR |------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

    /**
     * Constructor of the Masonry class
     *
     * @param createElementMethodCallback The callback to create the element
     * @param options The options to set the values of the Masonry layout, is optional
     */
    constructor( createElementMethodCallback, options = {} ) {
        //Set the options values if they are available, otherwise, set the default values
        this.selectorMasonry = options[ 'selector-masonry' ] || this.DEFAULT_SELECTOR_MASONRY;
        this.selectorImage = options[ 'selector-image' ] || this.DEFAULT_SELECTOR_IMAGE;
        this.dataKeyId = options[ 'data-key-id' ] || this.DEFAULT_DATA_KEY_ID;
        this.attrColumnHeight = options[ 'attr-column-height' ] || this.DEFAULE_ATTR_COLUMN_HEIGHT;
        this.columnCountMap = options[ 'column-count-map' ] || this.DEFAULT_COLUMN_COUNT_MAP;
        this.callbackHandlerScroll = options[ 'callback-handler-scroll' ] || this.handlerScroll;

        //Set the callback for the creation of the element and check if it is defined
        this.createElementMethodCallback = createElementMethodCallback;
        if( this.createElementMethodCallback === null ) throw new Error( `The callback for the creation of the element is not defined` )

        //Check if the options are defined
        if( this.selectorMasonry === null || this.selectorMasonry === '' ) throw new Error( `The selector for the Masonry container is not defined` );
        if( this.selectorImage === null || this.selectorImage === '' ) throw new Error( `The selector for the image is not defined` );
        if( this.dataKeyId === null || this.dataKeyId === '' ) throw new Error( `The ID key for the data is not defined` );
        if( this.attrColumnHeight === null || this.attrColumnHeight === '' ) throw new Error( `The attribute for the column height is not defined` );
        if( this.columnCountMap === null || this.columnCountMap.size === 0 ) throw new Error( `The column count map is not defined` );
        if( this.callbackHandlerScroll === null ) throw new Error( `The callback for the scroll event is not defined` );

        //Set the Masonry container and check if it is defined
        this.masonryContainer = document.querySelector( this.selectorMasonry );
        if( this.masonryContainer === null ) throw new Error(`Not found Masonry container with selector name: ${this.selectorMasonry}`);

        //Order the column count map by the key in descending order
        this.columnCountMap = new Map([...this.columnCountMap.entries()].sort((a, b) => b[0] - a[0]));

        //Init set variables to manage the data and the elements loaded
        this.data = [];
        this.elementsLoaded = [];
        this.loading = false;
        this.init();

        //Set the event listeners
        window.addEventListener('resize', this.handlerResizeEvent.bind( this ));
        window.addEventListener('scroll', this.handlerScrollEvent.bind( this ));
    }


//------------------------------------------------------------------------------------------------------------------- \\
//---| SETTERS |----------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

    setCallbackHandlerScroll( callback ){
        this.callbackHandlerScroll = callback;
    }



//------------------------------------------------------------------------------------------------------------------- \\
//---| GETTERS |----------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

    /**
     * Function to get the state of loading.
     *
     * @returns {boolean} The state of loading
     */
    isLoading() {
        return this.loading;
    }


    /**
     * Function to get the data that is filled in the Masonry layout.
     *
     * @returns {[]} The data that is filled in the Masonry layout
     */
    getData(){
        return this.data;
    }


    /**
     * Function to get the last data that is filled in the Masonry layout.
     *
     * @returns The last data that is filled in the Masonry layout
     */
    getLastData(){
        let result = null;

        //Check if the data is not empty
        if( this.data.length > 0 ){
            //Get the last data of the data array
            result = this.data[ this.data.length - 1 ];
        }
        return result;
    }


//------------------------------------------------------------------------------------------------------------------- \\
//---| METHODS |----------------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

    /**
     * Function to initialize the Masonry container
     */
    init() {
        this.clear();
        this.createColumns();
    }

    /**
     * Function to check if the document has a scroll
     *
     * @returns {boolean} True if the document has a scroll, otherwise, false
     */
    hasScroll() {
        const windwoHeight = window.innerHeight;
        const documentHeight = document.documentElement.scrollHeight;
        return documentHeight > windwoHeight;
    }


    /**
     * Function to clear the Masonry container and the data
     */
    clear() {
        this.data = [];
        this.elementsLoaded = [];
        this.loading = false;
        this.masonryContainer.innerHTML = '';
    }


    /**
     * Function to calculate the column count of the masonry layout.
     *
     * @returns {number} The column count of the masonry layout
     */
    calculateColumnCount() {
        let result = 0;
        const screenWidth = window.innerWidth;

        //Browse the column count map to get the column count based on the screen width
        for( const [key, value] of this.columnCountMap ){
            //Check if the screen width is greater than the key
            if( screenWidth >= key ){
                result = value;
                break;
            }
        }

        return result;
    }


    /**
     * Function to create the columns of the Masonry layout
     *
     * @param columnCount The column count to create the columns, by default calculate the column count
     */
    createColumns( columnCount = this.calculateColumnCount() ) {
        //Remove all content of the Masonry container
        this.masonryContainer.innerHTML = '';

        //Create the columns based on the column count
        for( let i = 0; i < columnCount; i++ ){
            //Create the column element as a div element
            const column = document.createElement( 'div' );
            column.classList.add( this.CLASS_NAME_MASONRY_COLUMN ); //Add the class name for the column
            column.setAttribute( this.attrColumnHeight, '0' ); //Add the attribute for the column height to 0

            //Append the column to the Masonry container
            this.masonryContainer.appendChild( column );
        }
    }


    /**
     * Function to find all columns elements of the Masonry layout
     *
     * @returns {NodeListOf<Element>} The columns elements of the Masonry layout
     */
    findAllColumns() {
        return document.querySelectorAll(`${this.selectorMasonry} > .${this.CLASS_NAME_MASONRY_COLUMN}` );
    }


    /**
     * Function to find the shortest column of the Masonry layout.
     *
     * @returns {Element} The shortest column of the Masonry layout
     */
    findShortestColumn() {
        //Get the columns of the Masonry layout
        const columns = this.findAllColumns();

        if( columns.length === 0 ) throw new Error( `Not found columns in the Masonry layout` );

        //Get first column as the shortest column
        let shortestColumn = columns[0];

        //Get the height of the shortest column in the attribute of the column to save the height
        let minHeight = parseInt( shortestColumn.getAttribute( this.attrColumnHeight ), 10 );

        //Browse the columns to get the shortest column
        columns.forEach(column => {
            //Get the height of the column
            const columnHeight = parseInt( column.getAttribute( this.attrColumnHeight ), 10 );

            //Check if the column height is less than the minimum height
            if( columnHeight < minHeight ){
                shortestColumn = column; //Set the column as the shortest column
                minHeight = columnHeight; //Set the height of the column as the minimum height
            }
        });

        return shortestColumn;
    }


    /**
     * Function to set the state of loading
     */
    startLoad() {
        this.loading = true;
    }


    /**
     * Function to finish the state of loading
     */
    finishLoad() {
        this.loading = false;
    }


    /**
     * Function to fill the Masonry layout with the same data already filled
     */
    refill(){
        const data = this.data;
        this.data = [];
        this.fill( data );
    }


    /**
     * Function to fill the Masonry layout with the data provided and the callback to create the element
     *
     * @param data The data to fill the Masonry layout
     */
    fill( data ){
        //Start the loading
        this.startLoad();

        //Add the data to the data array
        this.data = this.data.concat( data );

        //Browse the data to create the elements
        data.forEach( async itemData => {
            //Create the element with the data and the callback to create the element
            const element = this.createElementMethodCallback( itemData );

            //Get the image element of the element created by the selector image
            const image = element.querySelector( this.selectorImage );

            //Add the event listener to the image element to load the image
            image.addEventListener( 'load', ( event ) => {
                //Check if the element is already loaded
                if( this.elementsLoaded.find( target => target.id === element.id ) )
                    console.info( `The element with ID ${element.id} is already loaded` );

                //Set the loading attribute to lazy in the image element
                image.loading = 'lazy';

                //Add the element to the elements loaded array
                this.elementsLoaded.push( element );

                //Check if all elements are loaded
                if( this.elementsLoaded.length == data.length ){
                    //Browse the data to add the elements to the columns of the Masonry layout in order
                    data.forEach( elementData => {
                        //Find the element in the elements loaded array
                        const index = this.elementsLoaded.findIndex( target => target.id === elementData[ this.dataKeyId ] );

                        //Check if the element is found, otherwise, do nothing
                        if( index < 0 ) return;

                        //Get the element loaded and remove it from the elements loaded array
                        const elementLoaded = this.elementsLoaded[ index ];
                        this.elementsLoaded.splice( index, 1 );

                        //Add the element to the shortest column
                        const shortestColumn = this.findShortestColumn();
                        shortestColumn.appendChild( elementLoaded );

                        //Update the height of the shortest column with the height of the element loaded
                        const elementHeight = elementLoaded.clientHeight;
                        const updatedHeight = parseInt( shortestColumn.getAttribute( this.attrColumnHeight ), 10 ) + elementHeight;
                        shortestColumn.setAttribute( this.attrColumnHeight, updatedHeight.toString() );
                    });

                    //Finish the loading
                    this.finishLoad();
                }
            });
        });
    }


//------------------------------------------------------------------------------------------------------------------- \\
//---| EVENT HANDLERS |---------------------------------------------------------------------------------------------- \\
//------------------------------------------------------------------------------------------------------------------- \\

    /**
     * Function to handle the resize event. Reset the elements loaded, the loading state and the Masonry container with
     * the new calculated column and if the data is not empty, refill the Masonry layout.
     */
    handlerResizeEvent() {
        this.elementsLoaded = [];
        this.loading = false;
        this.masonryContainer.innerHTML = '';
        this.createColumns();
        if (this.data.length > 0) {
            this.refill();
        }
    }


    /**
     * Function to handle the scroll event. When the scroll is at the bottom of the page and the loading is false,
     * the callback to handle the scroll is called.
     */
    handlerScrollEvent(){
        if( window.innerHeight + window.scrollY >= document.body.offsetHeight - (window.innerHeight * 1.5) && !this.isLoading() ) {
            this.startLoad();
            this.callbackHandlerScroll();
        }
    }
}

//------------------------------------------------------------------------------------------------------------------- \\