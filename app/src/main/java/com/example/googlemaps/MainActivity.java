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

        CameraUpdate camUpd1 =
                CameraUpdateFactory
                       .newLatLngZoom(new LatLng(-1.01225, -79.46974), 18);
        mapa.moveCamera(camUpd1);
        mapa.setOnMapClickListener(this);

        //LatLng madrid = new LatLng(-1.01225, -79.46974);
        //CameraPosition camPos = new CameraPosition.Builder()
                //.target(madrid)
                //.zoom(20)
                //.bearing(45) //noreste arriba
                //.tilt(50) //punto de vista de la cámara 70 grados
                //.build();
        //CameraUpdate camUpd3 =
                //CameraUpdateFactory.newCameraPosition(camPos);
        //mapa.animateCamera(camUpd3);
        //mapa.setOnMapClickListener(this);

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