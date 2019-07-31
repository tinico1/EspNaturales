package android.example.espnaturales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spnComunidades;
    Spinner spnTipos;
    EspNatDbAccess dbAccess;
  //  EditText txtResult;



    void fillSpnTipos(EspNatDbAccess dbAccess, int idComunidad) {
        List<StringWithTag> list;

        list = dbAccess.getTiposRERB(idComunidad);
        ArrayAdapter<StringWithTag> adp1 = new ArrayAdapter<StringWithTag>(this,
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipos.setAdapter(adp1);

        spnTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag str = (StringWithTag) spnTipos.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), str.getString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "NOT selection", Toast.LENGTH_LONG).show();
            }
        });
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
                Toast.makeText(getApplicationContext(), str.getString(), Toast.LENGTH_LONG).show();
               fillSpnTipos( dbAccess, str.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "NOT selection", Toast.LENGTH_LONG).show();
               fillSpnTipos( dbAccess, 0);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnComunidades = findViewById(R.id.spnComunidades);
        spnTipos = findViewById(R.id.spinnerTipos);

        dbAccess = EspNatDbAccess.getInstance(getApplicationContext());
        dbAccess.open();
        fillSpnComunidades(dbAccess);

    }

    @Override
    protected void onDestroy() {
        dbAccess.close();
        super.onDestroy();
    }
}
