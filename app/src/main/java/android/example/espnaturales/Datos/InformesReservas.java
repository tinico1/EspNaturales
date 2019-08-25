package android.example.espnaturales.Datos;

public class InformesReservas {


    private int idInforme;
    private String tituloInforme;
    private String textoInforme;

    InformesReservas(int idInforme, String tituloInforme, String textoInforme) {
        this.idInforme = idInforme;
        this.tituloInforme = tituloInforme;
        this.textoInforme = textoInforme;
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
}
