package com.iuliocesars.ultralist.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;

import com.iuliocesars.ultralist.Modelos.Tabla;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "UltraList.db";
    private static final int DB_VERSION = 4;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private List<Tabla> ObtenerTabla(){
        List<Tabla> lstTablas = new ArrayList<>();

        lstTablas.add(new Tabla("Lista","CREATE TABLE \"Lista\" (\"id_lista\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, \"nombre\" TEXT, \"descripcion\" TEXT, \"presupuesto\" DOUBLE, \"privacidad\" INTEGER, \"usuario_agrego\" INTEGER, \"fecha_agrego\" TEXT, \"usuario_modifico\" INTEGER, \"fecha_modifico\" TEXT, \"estatus\" INTEGER)"));
        lstTablas.add(new Tabla("Articulo","CREATE TABLE \"Articulo\" (\"id_articulo\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, \"fk_lista\" INTEGER NOT NULL, \"nombre\" TEXT, \"descripcion\" TEXT, \"categoria\" TEXT, \"foto\" BLOB, \"precio\" DOUBLE, \"cantidad\" INTEGER, \"comprado\" INTEGER, \"es_oferta\" INTEGER, \"usuario_agrego\" INTEGER, \"fecha_agrego\" TEXT, \"usuario_modifico\" INTEGER, \"fecha_modifico\" TEXT, \"estatus\" INTEGER, \"image_path\" TEXT)"));

        return lstTablas;
    }


    /* La clase para hacer consultas SQLiteDatabase */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        List<Tabla> lstTablas = ObtenerTabla();
        for (Tabla tabla: lstTablas) {
            sqLiteDatabase.execSQL(tabla.getDeclaracion());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        List<Tabla> lstTablas = ObtenerTabla();
        for (Tabla tabla: lstTablas ) {
            sqLiteDatabase.execSQL("DROP TABLE " + tabla.getNombre());
        }
        onCreate(sqLiteDatabase);
    }
}
