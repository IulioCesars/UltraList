package com.iuliocesars.ultralist.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public interface InterfaceDAO<T>
{
    public boolean Agregar(T entidad);
    public boolean Modificar(T entidad);
    public boolean Eliminar(T entidad);
    public List<T> ObtenerTodo();
    ContentValues ObtenerContentValues(T entidad, boolean modificar);
    public T Obtener(int id);
    public T ObtenerEntidad(Cursor c);

}
