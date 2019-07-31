package android.example.espnaturales;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class EspNatSQLHelper  extends SQLiteAssetHelper {
    private static final String DATA_BASE_NAME = "Reservas.db";
    private static final int DATABASE_VERSION = 1;

    public EspNatSQLHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATABASE_VERSION);
    }
}
