package com.iuliocesars.ultralist.Util;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public class Result<T> {
    private Boolean exito;
    private T valor;
    private String mensaje;

    public Result(Boolean exito, T valor) {
        this.exito = exito;
        this.valor = valor;
    }

    public Result(Boolean exito, T valor, String mensaje) {
        this.exito = exito;
        this.valor = valor;
        this.mensaje = mensaje;
    }

    public Result()
    {}

    public Boolean getExito() {
        return exito;
    }

    public void setExito(Boolean exito) {
        this.exito = exito;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
