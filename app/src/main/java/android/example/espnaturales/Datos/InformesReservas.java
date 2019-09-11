package android.example.espnaturales.Datos;

import android.example.espnaturales.GlobalApplication;

public class InformesReservas {


    private int idInforme;
    private String tituloInforme;
    private String textoInforme;
    private String URLInforme;

    InformesReservas(int idInforme, String tituloInforme, String textoInforme, String URLInforme) {
        this.idInforme = idInforme;
        this.tituloInforme = tituloInforme;
        this.textoInforme = textoInforme;
        this.URLInforme = URLInforme;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public String getTituloInforme() {
        return tituloInforme;
    }

    public void setTituloInforme(String tituloInforme) {
        this.tituloInforme = tituloInforme;
    }

    public String getTextoInforme() {
        return textoInforme;
    }

    public void setTextoInforme(String textoInforme) {
        this.textoInforme = textoInforme;
    }

    public String getURLInforme(){
        return URLInforme;
    }
}
