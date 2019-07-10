package com.example.dell.projectx;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.projectx.ShowMemories.MyMemories;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 77;
    UserLocationProvider userLocationProvider;
    MarkerOptions markerOptions;
    private Button addBtn;
    private LatLng finalLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        userLocationProvider = new UserLocationProvider(this);

        markerOptions = new MarkerOptions();



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (isLocationAllowed()){
            Location userLocation = userLocationProvider.getUserLocation();
            LatLng userLatLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            finalLocation = new LatLng(userLatLng.latitude,userLatLng.longitude);
            markerOptions.position(userLatLng);
            mMap.addMarker(markerOptions);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));
        }else {
            RequestLocationPermession();
        }

    }

    boolean isLocationAllowed() {
        if (
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }

        return true;
    }


    private void RequestLocationPermession() {

        // an explanation only if the user has already denied that permission
        if (
            // that returns true if the user has previously denied the request,
            // and returns false if a user has denied a permission and selected
            // the Don't ask again option in the permission request dialog,
            // or if a device policy prohibits the permission.
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        } else {
            // this will first time if refused to give per will enter first condition
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Location userLocation = userLocationProvider.getUserLocation();
                        if (userLocation != null) {
                            LatLng userLatLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                            finalLocation = new LatLng(userLatLng.latitude,userLatLng.longitude);
                            markerOptions.position(userLatLng);
                            mMap.addMarker(markerOptions);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));
                        } else {
                            Toast.makeText(this, "No Location Found", Toast.LENGTH_SHORT).show();
                        }
                        //ShowMessage("Error", "Location not found");
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "cannot access location", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }

    }

    public void addMemory(View view){
        Intent intent = new Intent(MapActivity.this , MainActivity.class);
        intent.putExtra("lat" , finalLocation.latitude);
        intent.putExtra("long" , finalLocation.longitude);
        startActivity(intent);
    }
    public void ShowMyMemories(View view){
        Intent intent = new Intent(MapActivity.this, MyMemories.class);
        startActivity(intent);
    }


}
