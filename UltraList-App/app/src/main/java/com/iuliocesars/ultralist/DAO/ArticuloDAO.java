package com.iuliocesars.ultralist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Util.SuperContentValues;
import com.iuliocesars.ultralist.Util.SuperCursor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public class ArticuloDAO extends BaseDAO<Articulo> {
    private static final String t_articulo = "Articulo";
    private static final String c_id_articulo = "id_articulo";
    private static final String c_fk_lista = "fk_lista";
    private static final String c_nombre = "nombre";
    private static final String c_descripcion = "descripcion";
    private static final String c_categoria = "categoria";
    private static final String c_foto = "foto";
    private static final String c_precio = "precio";
    private static final String c_cantidad = "cantidad";
    private static final String c_comprado = "comprado";
    private static final String c_es_oferta = "es_oferta";
    private static final String c_usuario_agrego = "usuario_agrego";
    private static final String c_fecha_agrego = "fecha_agrego";
    private static final String c_usuario_modifico = "usuario_modifico";
    private static final String c_fecha_modifico = "fecha_modifico";
    private static final String c_estatus = "estatus";
    private static final String c_image_path = "image_path";

    public ArticuloDAO(Context context) {
        super(context);
    }

    @Override
    protected String ObtenerTabla() {
        return t_articulo;
    }

    @Override
    protected String ObtenerColumnaID() {
        return c_id_articulo;
    }

    @Override
    protected int ObtenerID(Articulo entidad) { return entidad.getId_articulo(); }

    @Override
    protected Articulo ObtenerEntidad(SuperCursor sc) {
        Articulo articulo = new Articulo();

        articulo.setId_articulo(sc.getInt(c_id_articulo));
        articulo.setFk_lista(sc.getInt(c_fk_lista));
        articulo.setNombre(sc.getString(c_nombre));
        articulo.setDescripcion(sc.getString(c_descripcion));
        articulo.setCategoria(sc.getString(c_categoria));
        articulo.setCantidad(sc.getInt(c_cantidad));
        articulo.setImage_path(sc.getString(c_image_path));
        articulo.setPrecio(sc.getBigDecimal(c_precio));
        articulo.setEs_oferta(sc.getBool(c_es_oferta));

        return articulo;
    }

    @Override
    protected ContentValues ObtenerContentValues(Articulo entidad, boolean modificar) {
        SuperContentValues cValues = new SuperContentValues();

        cValues.put(c_fk_lista, entidad.getFk_lista());
        cValues.put(c_nombre, entidad.getNombre());
        cValues.put(c_descripcion, entidad.getDescripcion());
        cValues.put(c_categoria, entidad.getCategoria());
        cValues.put(c_cantidad, entidad.getCantidad());
        cValues.put(c_precio, entidad.getPrecio());
        cValues.put(c_image_path, entidad.getImage_path());
        cValues.put(c_es_oferta, entidad.isEs_oferta());

        return cValues.getContentValues();
    }

    public List<Articulo> ObtenerLista(int idLista)
    {
        return ObtenerLista(String.format("%s = %s", c_fk_lista, idLista));
    }

    public List<Articulo> ObtenerOfertas()
    {
        return ObtenerLista(String.format("%s = %s", c_es_oferta, 1));
    }

    @Override
    protected void AsignarID(Articulo entidad, int ID) {
        entidad.setId_articulo(ID);
    }
}
