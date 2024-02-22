package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity
    implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    GoogleMap mapa;
    int contador;
    ArrayList<LatLng> puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        contador = 0;
        puntos = new ArrayList<LatLng>();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;

        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);

        LatLng[] coordenadaFacultad = {
                new LatLng(-1.012621359551321, -79.47048732760969),
                new LatLng(-1.012871436462689, -79.46931050837884),
                new LatLng(-1.0129572537450988, -79.46881027640472),
                new LatLng(-1.0123028969666923, -79.46883173408457),
        };
        float[] coloresMarcadores = {
                BitmapDescriptorFactory.HUE_RED,
                BitmapDescriptorFactory.HUE_RED,
                BitmapDescriptorFactory.HUE_RED,
                BitmapDescriptorFactory.HUE_RED
        };

        CameraUpdate camUpd1 =
                CameraUpdateFactory
                       .newLatLngZoom(coordenadaFacultad[2], 18);
        mapa.moveCamera(camUpd1);
        mapa.setOnMapClickListener(this);
        String[][] infoMarcadores = {
                {"AUDITORIO UTEQ"
                        ,"@drawable/auditoriouteq"},

                {"POSGRADO"
                        , "@drawable/postgrado"},

                {"TICS"
                        , "@drawable/tics"},

                {"BIBLIOTECA"
                        , "@drawable/biblioteca"}
        };
        for (int i=0; i<infoMarcadores.length; i++) {
            int indiceColor = i % coloresMarcadores.length;
            marcadorFacultad(coordenadaFacultad[i], infoMarcadores[i][0], coloresMarcadores[indiceColor]);
        }
    }

    private void marcadorFacultad(LatLng latLng, String s, float coloresMarcadore) {
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        //Projection proj = mapa.getProjection();
        //Point coord = proj.toScreenLocation(latLng);

        TextView lblLat = findViewById(R.id.lbl_latitud);
        lblLat.setText(String.format("%.8f",latLng.latitude));

        TextView lblLong = findViewById(R.id.lbl_longitud);
        lblLong.setText(String.format("%.8f",latLng.longitude));

       mapa.addMarker(new MarkerOptions().position(latLng )
               .title("Marcador"));
       contador = contador + 1;
       puntos.add(latLng);
       if(contador==4){
           PolylineOptions lineas = new PolylineOptions()
                   .add(puntos.get(0))
                   .add(puntos.get(1))
                   .add(puntos.get(2))
                   .add(puntos.get(3))
                   .add(puntos.get(0));
           lineas.width(8);
           lineas.color(Color.RED);
           mapa.addPolyline(lineas);
           contador = 0;
           puntos.clear();
        }

        //Toast.makeText(
                //MainActivity.this,
                //"Click\n" +
                        //"Lat: " + latLng.latitude + "\n" +
                        //"Lng: " + latLng.longitude + "\n" +
                        //"X: " + coord.x + " - Y: " + coord.y,
                //Toast.LENGTH_SHORT).show();
    }
    public void PintarRectYTEQ (View view) {

        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.011851, -79.471894)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.012916, -79.471817)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.013191, -79.467320)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.012272, -79.467103)));

        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.011851, -79.471894))
                .add(new LatLng(-1.012916, -79.471817))
                .add(new LatLng(-1.013191, -79.467320))
                .add(new LatLng(-1.012272, -79.467103))
                .add(new LatLng(-1.011851, -79.471894));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);

    }
}