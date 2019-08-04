package android.example.espnaturales.Datos;

public class StringWithTag {
    private String string;
    private int id;

    StringWithTag(String mystring, int miId) {
        string = mystring;
        id = miId;
    }

    public String getString() {
        return string;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return string;
    }
}
