package android.example.espnaturales;

import android.content.res.Resources;
import android.example.espnaturales.Datos.EspNatDbAccess;
import android.example.espnaturales.Datos.InformesReservas;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import static android.example.espnaturales.DetailActivity.EXTRA_POSITION;

public class InfDetActivity extends AppCompatActivity {

    public static final String INFO_EXTRA_ID = "ID";
    public static final String INFO_EXTRA_POSITION = "POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InformesReservas informesReservas;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_det);

        setSupportActionBar((Toolbar) findViewById(R.id.inf_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.inf_collapsing_toolbar);

        int id = getIntent().getIntExtra(INFO_EXTRA_ID, 0);
        int pos = getIntent().getIntExtra(INFO_EXTRA_POSITION, 0);

        informesReservas = InformeActivity.listaInformes.get(pos);
        Resources resources = getResources();

        TextView texto = findViewById(R.id.inf_texto_detail);
        texto.setText(informesReservas.getTextoInforme());

        collapsingToolbar.setTitle(informesReservas.getTituloInforme());
   /*
        ImageView placePicutre = findViewById(R.id.image);
        String uri = espacioNatural.getNomImagen();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        placePicutre.setImageDrawable(imagen);

*/

    }
}
