package android.example.espnaturales.Datos;

public class EspacioNatural {


    private int id;
    private String nombre;
    private int idTipo;

    private String nomImagen;
    private String description;
    private String descCorta;

    private String nomIcono;

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


}
