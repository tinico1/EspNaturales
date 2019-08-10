package android.example.espnaturales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actInformacion(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Informacion", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void actReservas(View view) {
        Intent intent = new Intent(this, TiposActivity.class);
        startActivity(intent);
    }
}
