package com.aipixel.api.common.datetime;

import java.time.format.DateTimeFormatter;



/**
 * Clase que proporciona formateadores de fecha y hora.
 */
public class LocalDateTimeFormatter {

    public static final DateTimeFormatter FULL_TIME_FORMATTER = DateTimeFormatter.ofPattern( "HH:mm:ss" );
    public static final DateTimeFormatter FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
    public static final DateTimeFormatter FULL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private LocalDateTimeFormatter() {
        throw new UnsupportedOperationException( "Contants Class" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
