package com.iuliocesars.ultralist.Modelos;

import android.media.Image;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IulioCesars on 10/03/2018.
 */

public class Articulo
{
    private int id_articulo;
    private int fk_lista;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Image foto;
    private BigDecimal precio;
    private int cantidad;
    private boolean comprado;
    private boolean es_oferta;
    //private String ubicacion;
    private int usuario_agrego;
    private Timestamp fecha_agrego;
    private int usuario_modifico;
    private Timestamp fecha_modifico;
    private int estatus;
}
