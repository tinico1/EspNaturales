package android.example.espnaturales;

import android.content.Intent;
import android.example.espnaturales.Datos.EspNatDbAccess;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EspNatDbAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAccess = EspNatDbAccess.getInstance(getApplicationContext());
    }

    public void actInformacion(View view) {
        Intent intent = new Intent(this, InformeActivity.class);
        startActivity(intent);

    }

    public void actReservas(View view) {
        Intent intent = new Intent(this, TiposActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
