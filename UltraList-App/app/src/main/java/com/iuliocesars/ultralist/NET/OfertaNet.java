package com.iuliocesars.ultralist.NET;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.iuliocesars.ultralist.Modelos.Oferta;
import com.iuliocesars.ultralist.Util.ConexionWS;
import com.iuliocesars.ultralist.Util.Result;

import java.util.List;

/**
 * Created by IulioCesars on 15/05/2018.
 */

public class OfertaNet extends BaseNet<Oferta>
{
    public OfertaNet(Context ActiviyContext) {
        super(ActiviyContext);
    }

    public void ObtenerLista(final INetAction<List<Oferta>> OnResponse)
    {
        AndroidNetworking.post(ObtenerURL(ConexionWS.ObtenerLista))
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObjectList(Oferta.class, new ParsedRequestListener<List<Oferta>>(){
                    @Override
                    public void onResponse(List<Oferta> response) {
                        if(response != null)
                        {
                            OnResponse.Execute(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ctx, anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected String ObtenerControlador() {
        return "Oferta";
    }
}
