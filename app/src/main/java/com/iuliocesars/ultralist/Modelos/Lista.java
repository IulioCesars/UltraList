package com.iuliocesars.ultralist.Modelos;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IulioCesars on 10/03/2018.
 */

public class Lista
{
    private int id_lista;
    private String nombre;
    private String descripcion;
    private BigDecimal presupuesto;
    private int privacidad;
    private int usuario_agrego;
    private Timestamp fecha_agrego;
    private int usuario_modifico;
    private Timestamp fecha_modifico;
    private int estatus;

    public Lista(int id_lista, String nombre, String descripcion, BigDecimal presupuesto, int usuario_agrego, Timestamp fecha_agrego, int usuario_modifico, Timestamp fecha_modifico, int estatus, int privacidad) {
        this.id_lista = id_lista;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.usuario_agrego = usuario_agrego;
        this.fecha_agrego = fecha_agrego;
        this.usuario_modifico = usuario_modifico;
        this.fecha_modifico = fecha_modifico;
        this.estatus = estatus;
        this.privacidad = privacidad;
    }

    public Lista(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
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

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
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

    public int getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(int privacidad) {
        this.privacidad = privacidad;
    }
}
