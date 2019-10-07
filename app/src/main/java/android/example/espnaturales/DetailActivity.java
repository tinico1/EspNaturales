/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.example.espnaturales;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.example.espnaturales.Datos.EspNatDbAccess;
import android.example.espnaturales.Datos.EspacioNatural;
import android.example.espnaturales.Utiles.Tools;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.example.espnaturales.ListaEspacios.urlForm;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_POSITION = "POSITION";
    static private boolean res = false;

    EspacioNatural getEspacio(int id) {
        int size = ListaEspacios.listaEspacios.size();
        for (int i =0; i< size; i++)
            if (ListaEspacios.listaEspacios.get(i).getId() == id)
                return ListaEspacios.listaEspacios.get(i);
        return null;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EspacioNatural espacioNatural;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!res)
                    dialogoEncuesta();
                else
                    backPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));

        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        int pos = getIntent().getIntExtra(EXTRA_POSITION, 0);

        espacioNatural = getEspacio(id);
        espacioNatural = EspNatDbAccess.getInstance(getApplicationContext()).getSetdescription(espacioNatural);
        Resources resources = getResources();

        collapsingToolbar.setTitle(espacioNatural.getNombre());

        TextView placeDetail = findViewById(R.id.place_detail);
        placeDetail.setText(espacioNatural.getDescription());


        TextView placeLocation = findViewById(R.id.place_location);
        placeLocation.setText(espacioNatural.getUbicacion());

        ImageView placePicutre = findViewById(R.id.image);
        Tools.getTools().setImage(espacioNatural.getNomImagen(), placePicutre);

        final TextView reservaURL = (TextView) findViewById(R.id.place_URL);
        reservaURL.setText(espacioNatural.getURL());
        Linkify.addLinks(reservaURL, Linkify.WEB_URLS);

    }
    private void dialogoEncuesta () {


        new AlertDialog.Builder(this)
                .setTitle("Nos das tu Opinión?")
                .setMessage("Ayuda a otros usuarios rellenando una encuesta")
                .setPositiveButton("De Acuerdo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent formulario = new Intent(getApplicationContext(), CuestionarioActivity.class);
                        formulario.putExtra(urlForm, "https://docs.google.com/forms/d/e/1FAIpQLScH1X3_7lf15peO8-Imq8px44VKxeNntS45dCQeM7Q69LUwtQ/viewform");
                        startActivity(formulario);
                        res = true;

                    }
                })
                .setNegativeButton("Ahora no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backPressed();
                    }
                }).show();

    }

    public void backPressed() {
        super.onBackPressed();
    }


    @Override
    public void onBackPressed() {
        if (!res)
            dialogoEncuesta();
        else
            backPressed();

    }
}
