package com.iuliocesars.ultralist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.Util.SuperContentValues;
import com.iuliocesars.ultralist.Util.SuperCursor;

import java.util.List;

/**
 * Created by IulioCesars on 29/04/2018.
 */

public class ListaDAO extends BaseDAO<Lista>
{
    private static final String t_lista = "Lista";
    private static final String c_id_lista = "id_lista";
    private static final String c_nombre = "nombre";
    private static final String c_descripcion = "descripcion";
    private static final String c_presupuesto = "presupuesto";
    private static final String c_privacidad = "privacidad";
    private static final String c_usuario_agrego = "usuario_agrego";
    private static final String c_fecha_agrego = "fecha_agrego";
    private static final String c_usuario_modifico = "usuario_modifico";
    private static final String c_fecha_modifico = "fecha_modifico";
    private static final String c_estatus = "estatus";

    public ListaDAO(Context context) {
        super(context);
    }

    @Override
    protected String ObtenerTabla() {
        return  t_lista;
    }

    @Override
    protected String ObtenerColumnaID() {
        return c_id_lista;
    }

    @Override
    protected int ObtenerID(Lista entidad) {
        return entidad.getId_lista();
    }

    @Override
    protected Lista ObtenerEntidad(SuperCursor sc) {
        Lista l =new Lista();
        l.setId_lista(sc.getInt(c_id_lista));
        l.setNombre(sc.getString(c_nombre));
        l.setDescripcion(sc.getString(c_descripcion));
        l.setPresupuesto(sc.getBigDecimal(c_presupuesto));
        l.setPrivacidad(sc.getInt(c_privacidad));
        l.setUsuario_agrego(sc.getInt(c_usuario_agrego));
        l.setFecha_agrego(sc.getTimeStamp(c_fecha_agrego));
        l.setUsuario_modifico(sc.getInt(c_usuario_modifico));
        l.setFecha_modifico(sc.getTimeStamp(c_fecha_modifico));
        l.setEstatus(sc.getInt(c_estatus));

        return l;
    }

    @Override
    protected ContentValues ObtenerContentValues(Lista entidad, boolean modificar) {
        SuperContentValues cv = new SuperContentValues();

        //cv.put(c_id_lista, entidad.getId_lista());
        cv.put(c_nombre, entidad.getNombre());
        cv.put(c_descripcion, entidad.getDescripcion());
        cv.put(c_presupuesto, entidad.getPresupuesto());
        cv.put(c_privacidad, entidad.getPrivacidad());
        cv.put(c_usuario_agrego, entidad.getUsuario_agrego());
        cv.put(c_fecha_agrego, entidad.getFecha_agrego());
        cv.put(c_usuario_modifico, entidad.getUsuario_modifico());
        cv.put(c_fecha_modifico, entidad.getFecha_modifico());
        cv.put(c_estatus, entidad.getEstatus());

        return cv.getContentValues();
    }

    @Override
    protected void AsignarID(Lista entidad, int ID) {
        entidad.setId_lista(ID);
    }

    @Override
    public boolean Eliminar(Lista entidad) {
        List<Articulo> lstArticulo = DAO.Articulo(ctx).ObtenerLista(entidad.getId_lista());

        for (Articulo a : lstArticulo)
        {
            DAO.Articulo(ctx).Eliminar(a);
        }

        return super.Eliminar(entidad);
    }
}
