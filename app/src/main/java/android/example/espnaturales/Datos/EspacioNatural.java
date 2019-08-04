package android.example.espnaturales.Datos;

public class EspacioNatural {


    private int id;
    private String nombre;
    private int idTipo;


    private String descCorta;

    public EspacioNatural(int id, String nombre, int idTipo, String descCorta) {
        this.id = id;
        this.nombre = nombre;
        this.idTipo = idTipo;
        this.descCorta = descCorta;
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

    @Override
    public String toString() {
        return nombre;
    }
}
