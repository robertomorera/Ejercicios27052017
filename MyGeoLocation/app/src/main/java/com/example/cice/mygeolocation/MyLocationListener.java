package com.example.cice.mygeolocation;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by cice on 27/5/17.
 */

public class MyLocationListener implements LocationListener {


    private MainActivity mainActivity;

    public MyLocationListener(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("MENSAJE","Location changed");
        mainActivity.mostrarLocalizacion(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("MENSAJE","Location changed");
        switch(status){
            case LocationProvider.AVAILABLE:
                Log.d("MENSAJE","Proveedor "+provider+ "DISPONIBLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("MENSAJE","Proveedor "+provider+ "FUERA DE SERVICIO");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("MENSAJE","Proveedor "+provider+ "NO DISPONIBLE TEMPORALMENTE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("MENSAJE","Provider enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("MENSAJE","Provider disabled");

    }
}
