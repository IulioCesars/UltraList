package com.iuliocesars.ultralist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iuliocesars.ultralist.Modelos.Articulo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public class ArticuloDAO extends SQLHelper implements InterfaceDAO<Articulo>{
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
    public boolean Agregar(Articulo entidad) {
        ContentValues cValues = ObtenerContentValues(entidad, false);

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(t_articulo, null, cValues);
        db.close();

        return id > 0;
    }

    @Override
    public boolean Modificar(Articulo entidad) {
        ContentValues cValues = ObtenerContentValues(entidad, true);

        String where = String.format("%s = %s", c_id_articulo, entidad.getId_articulo());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.update(t_articulo, cValues, where , null);
        db.close();

        return id > 0;
    }

    @Override
    public boolean Eliminar(Articulo entidad) {

        String where = String.format("%s = %s", c_id_articulo, entidad.getId_articulo());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.delete(t_articulo, where, null);
        db.close();

        return id > 0;
    }

    @Override
    public List<Articulo> ObtenerTodo() {
        List<Articulo> lstArticulo = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        String query = String.format("SELECT * FROM %s", t_articulo);
        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext())
        {
            Articulo articulo = ObtenerEntidad(c);
            lstArticulo.add(articulo);
        }

        db.close();

        return lstArticulo;
    }

    @Override
    public ContentValues ObtenerContentValues(Articulo entidad, boolean modificar) {
        ContentValues cValues = new ContentValues();

        cValues.put(c_fk_lista, entidad.getFk_lista());
        cValues.put(c_nombre, entidad.getNombre());
        cValues.put(c_descripcion, entidad.getDescripcion());
        cValues.put(c_categoria, entidad.getCategoria());
        cValues.put(c_cantidad, entidad.getCantidad());
        cValues.put(c_precio, entidad.getPrecio().doubleValue());
        cValues.put(c_image_path, entidad.getImage_path());

        return cValues;
    }

    @Override
    public Articulo Obtener(int id) {
        return null;
    }

    @Override
    public Articulo ObtenerEntidad(Cursor c) {
        Articulo articulo = new Articulo();

        articulo.setId_articulo(c.getInt(c.getColumnIndex(c_id_articulo)));
        articulo.setFk_lista(c.getInt(c.getColumnIndex(c_fk_lista)));
        articulo.setNombre(c.getString(c.getColumnIndex(c_nombre)));
        articulo.setDescripcion(c.getString(c.getColumnIndex(c_descripcion)));
        articulo.setCategoria(c.getString(c.getColumnIndex(c_categoria)));
        articulo.setCantidad(c.getInt(c.getColumnIndex(c_cantidad)));
        articulo.setImage_path(c.getString(c.getColumnIndex(c_image_path)));
        String precio = c.getString(c.getColumnIndex(c_precio));
        articulo.setPrecio(new BigDecimal(precio));


        return articulo;
    }
}
