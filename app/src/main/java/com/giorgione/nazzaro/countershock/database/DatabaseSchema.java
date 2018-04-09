package com.giorgione.nazzaro.countershock.database;

import android.provider.BaseColumns;

/**
 * Created by 91juv on 16/09/2016.
 */
public class DatabaseSchema {

    private DatabaseSchema() {
    }

    /* Inner class that defines the table contents */
    public class roadEntry implements BaseColumns {

        public static final String TABLE_NAME = "percorso";

        // Labels Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_partenza = "partenza";
        public static final String KEY_destinazione = "destinazione";
        public static final String KEY_fossi = "numero_fossi";
        public static final String KEY_km = "km";

    }
    /* Inner class that defines the table contents */
    public class userEntry implements BaseColumns {

        public static final String TABLE_NAME = "utenti";

        // Labels Table Columns names
        public static final String KEY_email = "email";
        public static final String KEY_nome = "name";
        public static final String KEY_cognome= "surname";
        public static final String KEY_password = "password";

    }

    public class feedbackEntry implements BaseColumns {

        public static final String TABLE_NAME = "feedback";

        // Labels Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_email = "email";
        public static final String KEY_road = "road";
        public static final String KEY_title = "title";
        public static final String KEY_body= "body";
        public static final String KEY_vote= "vote";

    }

}
