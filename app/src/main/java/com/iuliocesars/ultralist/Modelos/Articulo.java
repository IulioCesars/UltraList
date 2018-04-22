package com.iuliocesars.ultralist.Modelos;

import android.media.Image;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IulioCesars on 10/03/2018.
 */

public class Articulo implements Serializable
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

    public Articulo(int id_articulo, int fk_lista, String nombre, String descripcion, String categoria, Image foto, BigDecimal precio, int cantidad, boolean comprado, boolean es_oferta, int usuario_agrego, Timestamp fecha_agrego, int usuario_modifico, Timestamp fecha_modifico, int estatus) {
        this.id_articulo = id_articulo;
        this.fk_lista = fk_lista;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.foto = foto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.comprado = comprado;
        this.es_oferta = es_oferta;
        this.usuario_agrego = usuario_agrego;
        this.fecha_agrego = fecha_agrego;
        this.usuario_modifico = usuario_modifico;
        this.fecha_modifico = fecha_modifico;
        this.estatus = estatus;
    }

    public Articulo(String nombre, String descripcion, String categoria, BigDecimal precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getFk_lista() {
        return fk_lista;
    }

    public void setFk_lista(int fk_lista) {
        this.fk_lista = fk_lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }

    public boolean isEs_oferta() {
        return es_oferta;
    }

    public void setEs_oferta(boolean es_oferta) {
        this.es_oferta = es_oferta;
    }

    public int getUsuario_agrego() {
        return usuario_agrego;
    }

    public void setUsuario_agrego(int usuario_agrego) {
        this.usuario_agrego = usuario_agrego;
    }

    public Timestamp getFecha_agrego() {
        return fecha_agrego;
    }

    public void setFecha_agrego(Timestamp fecha_agrego) {
        this.fecha_agrego = fecha_agrego;
    }

    public int getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(int usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public Timestamp getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Timestamp fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
