package android.example.espnaturales;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class EspNatDbAccess {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;
    private static EspNatDbAccess instance;

    static private Cursor c = null;
    private EspNatDbAccess(Context context) {
        this.sqLiteOpenHelper = new EspNatSQLHelper(context);
    }

    public static EspNatDbAccess getInstance(Context context) {
        if(instance == null)
            instance = new EspNatDbAccess(context);
        return instance;
    }

    public void open() {
        this.db = this.sqLiteOpenHelper.getReadableDatabase();
    }

    public void close() {
        if(this.db != null) {
            this.db.close();
        }
    }


    // Obtener Comunidades
    List<StringWithTag> getComunidades() {

        List<StringWithTag> list =  new ArrayList<StringWithTag>();
        c = db.rawQuery("SELECT * from T_COMUNIDADES  ", new String[]{});
        StringWithTag string = new StringWithTag("", 0);
        list.add( string);
        while (c.moveToNext()) {
            StringWithTag stringWithTag = new StringWithTag(c.getString(1), c.getInt(0));
            list.add( stringWithTag);
        }
        return list;
    }

    // Obtener Tipos
    List<StringWithTag> getTiposRERB(int  idcomunidad) {
        String sql ;

        if(idcomunidad != 0) {
            sql = "select distinct tt.COD_TIPO_RERB, \n" +
                    "tt.NOM_TIPO_RERB \n" +
                    "FROM R_COM_ESP tr, \n" +
                    "T_ESPACIO te, \n" +
                    "T_TIPO_RERB tt WHERE tr.MAB_CODE = te.COD_ESPACIO AND tt.COD_TIPO_RERB = te.COD_TIPO_RERB AND tr.CODIGO = " + idcomunidad +
                    " order by tt.COD_TIPO_RERB";
        }
        else {
            sql =   "SELECT DISTINCT tp.COD_TIPO_RERB, tp.NOM_TIPO_RERB FROM  T_TIPO_RERB  tp";
        }

        List<StringWithTag> list =  new ArrayList<StringWithTag>();
        c = db.rawQuery(sql, new String[]{});
        StringWithTag string = new StringWithTag("", 0);
        while (c.moveToNext()) {
            StringWithTag stringWithTag = new StringWithTag(c.getString(1), c.getInt(0));
            list.add(stringWithTag);
        }
        return list;
    }
}
