package android.example.espnaturales;

import android.content.Context;
import android.content.Intent;
import android.example.espnaturales.Datos.EspacioNatural;
import android.example.espnaturales.Utiles.Tools;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    static final private float ZOOM = 7.5f;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                mMap.clear(); //clear old markers
                int numMarkers = 0;

                int listaSize = ListaEspacios.listaEspacios.size();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (int i = 0; i < listaSize; i++) {
                    float lngAct = ListaEspacios.listaEspacios.get(i).getLongitud();
                    float latAct = ListaEspacios.listaEspacios.get(i).getLatitud();
                    String titulo = ListaEspacios.listaEspacios.get(i).getNombre();
                    int tipoRERB = ListaEspacios.listaEspacios.get(i).getIdTipo();

                    if (lngAct != 0) {
                        numMarkers++;
                        Marker nmark = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(latAct, lngAct))
                                .title(titulo)
                                .icon(BitmapDescriptorFactory.defaultMarker(Tools.getTools().getTipoColor( tipoRERB))));
                        nmark.setTag(ListaEspacios.listaEspacios.get(i));
                        builder.include(new LatLng(latAct, lngAct));
                    }
                }


                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    boolean doubleBackToExitPressedOnce = false;

                    @Override
                    public boolean onMarkerClick(Marker m) {

                        if (doubleBackToExitPressedOnce) {
                            EspacioNatural espacioNatural = (EspacioNatural) m.getTag();
                            Intent intent = new Intent(getContext(), DetailActivity.class);

                            int id = espacioNatural.getId();
                            intent.putExtra(DetailActivity.EXTRA_ID,
                                    id);

                            getContext().startActivity(intent);
                        } else {
                            this.doubleBackToExitPressedOnce = true;

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    doubleBackToExitPressedOnce = false;
                                }
                            }, 1000);
                        }
                        return false;
                    }
                });
                if (numMarkers == 0) {
                    CameraPosition googlePlex = CameraPosition.builder()
                            .target(new LatLng(40.4167f,  -3.70325f))
                            .zoom(5)
                            .bearing(0)
                            .tilt(45)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);

                    Toast toast = Toast.makeText(GlobalApplication.getAppContext(), "No hay ubicaciones asignadas para esta selección", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    LatLngBounds      bounds = builder.build();
                    if (numMarkers == 1) {
                        CameraPosition googlePlex = CameraPosition.builder()
                                .target(bounds.getCenter())
                                .zoom(ZOOM)
                                .bearing(0)
                                .tilt(45)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
                    } else {
                        int padding = 100; // offset from edges of the map in pixels

                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                        mMap.animateCamera(cu, 1000, null);
                    }
                }
            }
        });

        return rootView;
    }
}