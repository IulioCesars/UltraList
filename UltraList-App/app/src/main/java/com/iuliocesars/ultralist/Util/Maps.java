package com.iuliocesars.ultralist.Util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by IulioCesars on 16/05/2018.
 */

public class Maps
{
    public static LatLng getCurrentLocation(Context ctx) throws SecurityException {
        LocationManager locationManager  = (LocationManager) ctx.getSystemService(LOCATION_SERVICE);

        // GPS, Wifi, Datos, Bluetooth
        List<String> lstProviders = locationManager.getProviders(true);
        // LocationManager da toda la info de ubicacion

        Location bestLocation = null;

        for (String provider : lstProviders){
            Location l = locationManager.getLastKnownLocation(provider);
            // Devuelve la ultima ubicacion conocida por la app

            if(l == null)
            { continue; }

            if(bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy())
            { bestLocation = l; }
        }

        if(bestLocation == null)
        {  return null; }

        return new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
    }
}
