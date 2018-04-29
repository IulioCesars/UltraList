package com.iuliocesars.ultralist.DAO;

import android.content.Context;

/**
 * Created by IulioCesars on 29/04/2018.
 */

public class DAO
{
    public static ArticuloDAO Articulo(Context ctx)
    { return new ArticuloDAO(ctx); }

    public static ListaDAO Lista(Context ctx)
    { return new ListaDAO(ctx); }
}
