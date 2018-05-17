package com.iuliocesars.ultralist.Modelos;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IulioCesars on 15/05/2018.
 */

public class Oferta implements Serializable
{
    public int id_oferta;
    public int id_articulo;
    public String nombre;
    public String descripcion;
    public String categoria;
    public BigDecimal precio;
    public int usuario_agrego;
    public Timestamp fecha_agrego;
    public int usuario_modifico;
    public Timestamp fecha_modifico;
    public int estatus;
    public String image_path;
    public double lat;
    public double lng;



    public void SetUbicacion(LatLng ll)
    {
        this.lat = ll.latitude;
        this.lng = ll.longitude;
    }

    public LatLng GetUbicacion()
    {
        return new LatLng(lat, lng);
    }
}
