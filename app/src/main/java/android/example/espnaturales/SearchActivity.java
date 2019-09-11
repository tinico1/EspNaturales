package android.example.espnaturales;

import android.content.Intent;
import android.example.espnaturales.Datos.EspNatDbAccess;
import android.example.espnaturales.Datos.StringWithTag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    final static String TIPO = "TIPO";
    final static String REGION = "REGION";

    Spinner spnComunidades;
    Spinner spnTipos;
    Button btnBuscar;

    EspNatDbAccess dbAccess;
    private int mRegion = 0;
    private int mTipoEspacio = 0;

    void fillSpnTipos(EspNatDbAccess dbAccess, int idComunidad) {
        List<StringWithTag> list;

        list = dbAccess.getTiposRERB(idComunidad);

        if(list.size()==1){
            Toast.makeText(getApplicationContext(), "No hay Reservas en esta comunidad", Toast.LENGTH_LONG).show();
            btnBuscar.setEnabled(false);
        }

        else {
            btnBuscar.setEnabled(true);
            ArrayAdapter<StringWithTag> adp1 = new ArrayAdapter<StringWithTag>(this,
                    android.R.layout.simple_list_item_1, list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTipos.setAdapter(adp1);

            spnTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    StringWithTag str = (StringWithTag) spnTipos.getItemAtPosition(position);

                    mTipoEspacio = str.getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    mTipoEspacio = 0;
                }
            });
        }
    }

    void fillSpnComunidades(final EspNatDbAccess dbAccess) {
        String comunidad;
        List<StringWithTag> list;

        list = dbAccess.getComunidades();
        ArrayAdapter<StringWithTag> adp1 = new ArrayAdapter<StringWithTag>(this,
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnComunidades.setAdapter(adp1);

        spnComunidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag str = (StringWithTag) spnComunidades.getItemAtPosition(position);
                mRegion = str.getId();
                fillSpnTipos(dbAccess, mRegion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "NOT selection", Toast.LENGTH_LONG).show();
                mRegion = 0;
                fillSpnTipos(dbAccess, mRegion);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        spnComunidades = findViewById(R.id.spnComunidades);
        spnTipos = findViewById(R.id.spinnerTipos);
        btnBuscar = findViewById(R.id.button);

        dbAccess = EspNatDbAccess.getInstance(getApplicationContext());
        dbAccess.open();
        fillSpnComunidades(dbAccess);

    }


    @Override
    protected void onDestroy() {
        dbAccess.close();
        super.onDestroy();
    }


    public void actSearch(View view) {
        Intent intent = new Intent(this, ListaEspacios.class);
        intent.putExtra(REGION, mRegion);
        intent.putExtra(TIPO, mTipoEspacio);
        startActivity(intent);
    }
}
