package android.example.espnaturales.Datos;

import android.example.espnaturales.GlobalApplication;

public class EspacioNatural {


    private int id;
    private String nombre;
    private int idTipo;



    private String nomImagen;
    private String description;
    private String descCorta;

    private String nomIcono;

    private String ubicacion = null;
    private String url = null;
    private float longitud = 0;
    private float latitud = 0;

    EspacioNatural(int id, String nombre, int idTipo, String descCorta, String description, String nomImagen, String nomIcono) {
        this.id = id;
        this.nombre = nombre;
        this.idTipo = idTipo;
        this.descCorta = descCorta;
        this.description = null;
        this.nomImagen = nomImagen;
        this.nomIcono = nomIcono;

    }

    public String getNomIcono() {
        return nomIcono;
    }

    public void setNomIcono(String nomIcono) {
        this.nomIcono = nomIcono;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getDescCorta() {
        return descCorta;
    }

    public void setDescCorta(String descCorta) {
        this.descCorta = descCorta;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public String getNomImagen() {
        return nomImagen;
    }

    public void setNomImagen(String nomImagen) {
        this.nomImagen = nomImagen;
    }

    @Override
    public String toString() {
        return nombre;
    }
    public float getLongitud() {
        if(longitud ==0)
            longitud = EspNatDbAccess.getInstance(GlobalApplication.getAppContext()).getLongitud(id);
        return longitud;
    }

    public float getLatitud() {
        if(latitud ==0)
            latitud = EspNatDbAccess.getInstance(GlobalApplication.getAppContext()).getLatitud(id);
        return latitud;
    }


    public String getUbicacion() {
        if(ubicacion == null)
            ubicacion = EspNatDbAccess.getInstance(GlobalApplication.getAppContext()).getUbicacion(id);
        return ubicacion;
    }
    public String getURL() {
        if(url == null)
            url = EspNatDbAccess.getInstance(GlobalApplication.getAppContext()).getURL(id);
        return url;
    }

}
