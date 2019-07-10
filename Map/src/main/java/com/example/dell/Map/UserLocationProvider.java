package com.example.dell.projectx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class UserLocationProvider implements LocationListener  {

    private final int MIN_TIME_BETWEEN_UPDATES = 1000;
    private final int MIN_DISTANCE_BETWEEN_UPDATES = 5;

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    private LocationManager locationManager;
    Context context;
    private Location location;
    private boolean canGetLocatin;

    OnMyLocationChangeListener onLocationChangedListener;

    public void setOnLocationChangedListener(OnMyLocationChangeListener onLocationChangedListener) {
        this.onLocationChangedListener = onLocationChangedListener;
    }

    public UserLocationProvider(Context context) {
        this.context = context;
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        location = null;
        canGetLocatin = false;
    }

    public boolean isGPSProviderEnabled() {
        return
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public boolean isNetworkProviderEnabled() {
        return
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public boolean canGetUserLocation() {
        canGetLocatin =
                isNetworkProviderEnabled() || isGPSProviderEnabled();
        return canGetLocatin;
    }

    @SuppressLint("MissingPermission") // will use only when have granted we have permission
    public Location getUserLocation() {
        if (!isGPSProviderEnabled() && !isNetworkProviderEnabled()) {
            canGetLocatin = false;
            return null;
        }
        String userEnabledProvider = null;

        if (isNetworkProviderEnabled())
            userEnabledProvider = LocationManager.NETWORK_PROVIDER;

        if (isGPSProviderEnabled())
            userEnabledProvider = LocationManager.GPS_PROVIDER;


        // if we want get updates when user change location
        locationManager.requestLocationUpdates(userEnabledProvider, MIN_TIME_BETWEEN_UPDATES, MIN_DISTANCE_BETWEEN_UPDATES, this);


        // can return null at firstly because there in location at first
        // can check if location null
        location = getLastKnownLocation();


        return location;
    }


    @SuppressLint("MissingPermission")
    public Location getLastKnownLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

        this.location = location;
        //Log.e("Location", location.getLatitude() + "..." + location.getLongitude());
        // for tracking if want later
//        if (onLocationChangedListener != null)
//            onLocationChangedListener.LocationChanged(location);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.e("locationlistener", s + " is enabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.e("locationlistener", s + " is disabled");

    }


    interface OnMyLocationChangeListener {
        void LocationChanged(Location newLocation);
    }





//    @SuppressLint("MissingPermission")
//    public Location getLastKnowLocation() {
//        String userEnabledProvider = null;
//        if (isNetworkProviderEnabled())
//            userEnabledProvider = LocationManager.NETWORK_PROVIDER;
//
//        if (isGPSProviderEnabled())
//            userEnabledProvider = LocationManager.GPS_PROVIDER;
//        return locationManager.getLastKnownLocation(userEnabledProvider);
//
//    }
}
