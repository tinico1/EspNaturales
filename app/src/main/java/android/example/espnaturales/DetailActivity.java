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

import android.content.res.Resources;
import android.example.espnaturales.Datos.EspNatDbAccess;
import android.example.espnaturales.Datos.EspacioNatural;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_POSITION = "POSITION";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EspacioNatural espacioNatural;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));

        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        int pos = getIntent().getIntExtra(EXTRA_POSITION, 0);

        espacioNatural = ListaEspacios.listaEspacios.get(pos);
        espacioNatural = EspNatDbAccess.getInstance(getApplicationContext()).getSetdescription(espacioNatural);
        Resources resources = getResources();

        collapsingToolbar.setTitle(espacioNatural.getNombre());


        TextView placeDetail = findViewById(R.id.place_detail);
        placeDetail.setText(espacioNatural.getDescription());

        String[] placeLocations = resources.getStringArray(R.array.place_locations);
        TextView placeLocation = findViewById(R.id.place_location);
        placeLocation.setText(placeLocations[pos % placeLocations.length]);

        ImageView placePicutre = findViewById(R.id.image);
        String uri = espacioNatural.getNomImagen();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        placePicutre.setImageDrawable(imagen);


        //  placePictures.recycle();
    }
}