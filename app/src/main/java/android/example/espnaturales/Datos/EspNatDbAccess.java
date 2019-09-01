package android.example.espnaturales.Datos;

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

    private static boolean dbAbierta = false;

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
        if (!dbAbierta) {
            this.db = this.sqLiteOpenHelper.getWritableDatabase();
            dbAbierta = true;
        }

    }

    public void close() {
        if ((this.db != null) && dbAbierta) {
            this.db.close();
        }
        dbAbierta = false;
    }


    // Obtener Comunidades
    public List<StringWithTag> getComunidades() {

        open();

        List<StringWithTag> list =  new ArrayList<StringWithTag>();
        c = db.rawQuery("SELECT * from T_COMUNIDADES  ", new String[]{});
        StringWithTag string = new StringWithTag("", 0);
        list.add( string);
        while (c.moveToNext()) {
            StringWithTag stringWithTag = new StringWithTag(c.getString(1), c.getInt(0));
            list.add(stringWithTag);
        }
        close();
        return list;
    }


    // Obtener Tipos
    public List<StringWithTag> getTiposRERB(int idcomunidad) {
        String sql ;

        if(idcomunidad != 0) {
            sql = "select distinct tt.COD_TIPO_RERB, \n" +
                    "tt.NOM_TIPO_RERB \n" +
                    "FROM R_COM_ESP tr, \n" +
                    "T_ESPACIO te, \n" +
                    "T_TIPO_RERB tt WHERE tr.COD_ESPACIO = te.COD_ESPACIO AND tt.COD_TIPO_RERB = te.COD_TIPO_RERB AND tr.COD_COMUNIDAD = " + idcomunidad +
                    " order by tt.COD_TIPO_RERB";
        } else {
            sql =   "SELECT DISTINCT tp.COD_TIPO_RERB, tp.NOM_TIPO_RERB FROM  T_TIPO_RERB  tp";
        }

        List<StringWithTag> list =  new ArrayList<StringWithTag>();
        open();
        c = db.rawQuery(sql, new String[]{});
        StringWithTag stringWithTag = new StringWithTag("", 0);
        list.add(stringWithTag);
        while (c.moveToNext()) {
            stringWithTag = new StringWithTag(c.getString(1), c.getInt(0));
            list.add(stringWithTag);
        }
        close();
        return list;
    }

    public List<EspacioNatural> getListaEspacios(int idcomunidad, int idtipo) {
        String sql;

        if ((idcomunidad != 0) && (idtipo != 0)) {
            sql = "SELECT DISTINCT esp.COD_ESPACIO, esp.NOM_ESPACIO, esp.COD_TIPO_RERB, esp.DESC_ESPACIO, ifnull(esp.NOM_IMAGEN, trfb.IMAGEN_REFB), trfb.ICONO_RFEB FROM T_ESPACIO esp, R_COM_ESP rcom, T_TIPO_RERB trfb WHERE esp.COD_TIPO_RERB = " + idtipo + " and rcom.COD_COMUNIDAD = " + idcomunidad + " and rcom.COD_ESPACIO = esp.COD_ESPACIO and trfb.COD_TIPO_RERB = esp.COD_TIPO_RERB";
        } else if ((idcomunidad != 0) && (idtipo == 0)) {
            sql = "SELECT DISTINCT esp.COD_ESPACIO, esp.NOM_ESPACIO, esp.COD_TIPO_RERB, esp.DESC_ESPACIO, ifnull(esp.NOM_IMAGEN, trfb.IMAGEN_REFB) , trfb.ICONO_RFEB  FROM T_ESPACIO esp, R_COM_ESP rcom, T_TIPO_RERB trfb WHERE  rcom.COD_COMUNIDAD =  " + idcomunidad + " and rcom.COD_ESPACIO = esp.COD_ESPACIO and trfb.COD_TIPO_RERB = esp.COD_TIPO_RERB";
        } else if ((idcomunidad == 0) && (idtipo != 0)) {
            sql = "SELECT DISTINCT esp.COD_ESPACIO, esp.NOM_ESPACIO, esp.COD_TIPO_RERB, esp.DESC_ESPACIO, ifnull(esp.NOM_IMAGEN, trfb.IMAGEN_REFB), trfb.ICONO_RFEB  NOM_IMAGEN  FROM T_ESPACIO esp, T_TIPO_RERB trfb  WHERE trfb.COD_TIPO_RERB = esp.COD_TIPO_RERB and esp.COD_TIPO_RERB = " + idtipo;
        } else {
            sql = "SELECT DISTINCT esp.COD_ESPACIO, esp.NOM_ESPACIO, esp.COD_TIPO_RERB, esp.DESC_ESPACIO, ifnull(esp.NOM_IMAGEN, trfb.IMAGEN_REFB), trfb.ICONO_RFEB  NOM_IMAGEN  FROM T_ESPACIO esp, T_TIPO_RERB trfb  WHERE trfb.COD_TIPO_RERB = esp.COD_TIPO_RERB  ";
        }

        open();
        List<EspacioNatural> list = new ArrayList<EspacioNatural>();
        c = db.rawQuery(sql, new String[]{});

        while (c.moveToNext()) {

            EspacioNatural espacioNatural = new EspacioNatural(c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3),
                    c.getString(3),
                    c.getString(4),
                    c.getString(5));
            list.add(espacioNatural);
        }
        close();
        return list;

    }

    public EspacioNatural getSetdescription(EspacioNatural espacioNatural) {
        open();
        String des = espacioNatural.getDescription();

        if (des == null) {
            String sql = "SELECT  esp.DESC_ESPACIO FROM T_ESPACIO esp  WHERE esp.COD_ESPACIO = " + espacioNatural.getId();
            c = db.rawQuery(sql, new String[]{});
            while (c.moveToNext() && (espacioNatural.getDescription() == null)) {
                espacioNatural.setDescription(c.getString(0));
            }
        }
        close();
        return espacioNatural;
    }


    // Obtener Informes
    public List<InformesReservas> getInformes() {

        open();

        List<InformesReservas> list = new ArrayList<InformesReservas>();
        c = db.rawQuery("SELECT id_infor, nom_infor, desc_infor from T_INFORMES  ", new String[]{});

        while (c.moveToNext()) {
            InformesReservas informe = new InformesReservas(c.getInt(0), c.getString(1), c.getString(2));
            list.add(informe);
        }
        close();
        return list;
    }

}
