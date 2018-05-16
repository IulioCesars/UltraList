package com.iuliocesars.ultralist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Util.SuperCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IulioCesars on 29/04/2018.
 */

public abstract class BaseDAO<T> extends SQLHelper
{
    protected Context ctx;

    public BaseDAO(Context context)
    {
        super(context);
        ctx = context;
    }

    public boolean Agregar(T entidad) {
        ContentValues cValues = ObtenerContentValues(entidad, false);

        SQLiteDatabase db = getWritableDatabase();
        Long id = db.insert(ObtenerTabla(), null, cValues);

        db.close();

        if(id > 0) {
            AsignarID(entidad, id.intValue());
            return true;
        }
        else {
            return false;
        }
    }

    public boolean Modificar(T entidad) {
        ContentValues cValues = ObtenerContentValues(entidad, true);

        String where = String.format("%s = %s", ObtenerColumnaID(), ObtenerID(entidad) );

        SQLiteDatabase db = getWritableDatabase();
        long id = db.update(ObtenerTabla(), cValues, where , null);
        db.close();

        return id > 0;
    }

    public boolean Eliminar(T entidad) {
        String where = String.format("%s = %s", ObtenerColumnaID(),ObtenerID(entidad) );

        SQLiteDatabase db = getWritableDatabase();
        long id = db.delete(ObtenerTabla(), where, null);
        db.close();

        return id > 0;
    }


    public List<T> ObtenerTodo() {
        return ObtenerLista(null);
    }



    protected List<T> ObtenerLista(String where) {
        List<T> lst = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        if(where == null)
        { where = ""; }
        else
        { where = "WHERE " + where; }

        String query = String.format("SELECT * FROM %s ", ObtenerTabla()) + where;
        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext())
        {
            T e = ObtenerEntidad(new SuperCursor(c));
            lst.add(e);
        }

        db.close();

        return lst;
    }

    public T Obtener(int id) {
        return null;
    }

    protected abstract String ObtenerTabla();

    protected abstract String ObtenerColumnaID();

    protected abstract int ObtenerID(T entidad);

    protected abstract T ObtenerEntidad(SuperCursor sc);

    protected abstract ContentValues ObtenerContentValues(T entidad, boolean modificar);

    protected abstract void AsignarID(T entidad, int ID);

}
