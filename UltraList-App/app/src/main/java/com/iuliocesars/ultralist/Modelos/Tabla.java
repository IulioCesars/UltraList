package com.iuliocesars.ultralist.Modelos;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public class Tabla
{
    private String nombre;
    private String declaracion;

    public Tabla(String nombre, String declaracion) {
        this.nombre = nombre;
        this.declaracion = declaracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeclaracion() {
        return declaracion;
    }

    public void setDeclaracion(String declaracion) {
        this.declaracion = declaracion;
    }
}