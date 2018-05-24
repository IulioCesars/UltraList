package com.iuliocesars.ultralist.NET;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.ConexionWS;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.Result;
import com.jacksonandroidnetworking.JacksonParserFactory;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by IulioCesars on 14/05/2018.
 */

public abstract class BaseNet<T>
{
    protected Context ctx;
    protected ProgressDialog m_progressDialog;

    public BaseNet(Context ActiviyContext) {
        ctx = ActiviyContext;

        AndroidNetworking.initialize(ctx);

        // Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
                //.addNetworkInterceptor(new StethoInterceptor())
                .build();
        AndroidNetworking.initialize(ctx,okHttpClient);

        AndroidNetworking.setParserFactory(new JacksonParserFactory());
    }

    protected abstract String ObtenerControlador();


    protected String ObtenerURL(String Accion) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String URL = preferences.getString(Extras.WebService, "");
        return URL + "/api/" + ObtenerControlador() + "/" + Accion;
    }

    public void Agregar(T entidad, final INetAction<Boolean> OnResponse) {
        MostrarMensajeProgreso(R.string.txtGuardando, R.string.txtPorFavorEspere);

        AndroidNetworking.post(ObtenerURL(ConexionWS.Agregar))
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(entidad)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(Result.class, new ParsedRequestListener<Result<Boolean>>(){
                    @Override
                    public void onResponse(Result<Boolean> response) {
                        if(response != null && response.getExito())
                        {
                            OnResponse.Execute(response.getValor());
                            CerrarMenajeProgreso();
                        }
                        else
                        {
                            CerrarMenajeProgreso();
                            Toast.makeText(ctx, response.getMensaje(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        CerrarMenajeProgreso();
                        new Exception(anError.getMessage());
                    }
                });
    }



    protected void MostrarMensajeProgreso(int titulo, int msg)
    {
        m_progressDialog = new ProgressDialog(ctx);

        m_progressDialog.setTitle(ctx.getResources().getString(titulo));
        m_progressDialog.setMessage(ctx.getResources().getString(msg));

        m_progressDialog.setCancelable(false);

        m_progressDialog.show();
    }

    protected void CerrarMenajeProgreso()
    { m_progressDialog.dismiss(); }
}
