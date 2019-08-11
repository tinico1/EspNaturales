package android.example.espnaturales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static android.example.espnaturales.SearchActivity.REGION;
import static android.example.espnaturales.SearchActivity.TIPO;

public class TiposActivity extends AppCompatActivity {

    private int mRegion = 0;
    private int mTipoEspacio = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos);
    }

    //Al presionar muestra una lista con todos las Reservas de la Biosfera
    public void actLista(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Lista Completa", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, ListaEspacios.class);
        intent.putExtra(REGION, mRegion);
        intent.putExtra(TIPO, mTipoEspacio);
        startActivity(intent);

    }

    //Al presionar nos saca una pantalla para selecionar una Comunidad y que nos de todas las de esa comunidad
    public void actComunidad(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Busqueda por Comunidad", Toast.LENGTH_SHORT);
        toast.show();

    }
    //Al presionar nos saca una pantalla para selecionar un Tipo de Reserva y que nos de todas las de ese tipo

    public void actTipo(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Busqueda por tipo", Toast.LENGTH_SHORT);
        toast.show();

    }

    //Al presionar nos saca una pantalla para buscar por tipo y comunidad
    public void actBusCombi(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void actBoton3(View view) {
    }

    public void actBoton4(View view) {
    }
}
