package com.iuliocesars.ultralist.NET;

import android.content.Context;

import com.iuliocesars.ultralist.Modelos.Lista;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by IulioCesars on 14/05/2018.
 */

public class ListaNet extends BaseNet<Lista> {
    public ListaNet(Context ActiviyContext) {
        super(ActiviyContext);
    }

    @Override
    protected String ObtenerControlador() {
        return null;
    }

}
