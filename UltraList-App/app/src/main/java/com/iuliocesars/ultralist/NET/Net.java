package com.iuliocesars.ultralist.NET;

import android.content.Context;

/**
 * Created by IulioCesars on 14/05/2018.
 */

public class Net {

    public static ArticuloNet Articulo(Context ctx)
    { return new ArticuloNet(ctx); }

    public static ListaNet Lista(Context ctx)
    { return new ListaNet(ctx); }

    public static OfertaNet Oferta(Context ctx)
    { return new OfertaNet(ctx); }

}
