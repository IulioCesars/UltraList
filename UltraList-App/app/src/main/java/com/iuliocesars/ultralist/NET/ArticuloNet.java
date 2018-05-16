package com.iuliocesars.ultralist.NET;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Adapter;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Oferta;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.ConexionWS;
import com.iuliocesars.ultralist.Util.Result;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import okhttp3.OkHttpClient;


/**
 * Created by IulioCesars on 14/05/2018.
 */

public class ArticuloNet extends BaseNet<Articulo>
{
    private final String CompartirOferta = "CompartirOferta";
    private final String EnviarImagen = "EnviarImagen";

    public ArticuloNet(Context ActiviyContext) {
        super(ActiviyContext);
    }

    @Override
    protected String ObtenerControlador() {
        return "Articulo";
    }

    public void CompartirOferta(final Articulo articulo, final INetAction<Integer> OnResponse) {
        MostrarMensajeProgreso(R.string.txtGuardando, R.string.txtPorFavorEspere);

        if(articulo.getFecha_agrego() == null)
            articulo.setFecha_agrego(new Timestamp(System.currentTimeMillis()));

        if(articulo.getFecha_modifico() == null)
            articulo.setFecha_modifico(new Timestamp(System.currentTimeMillis()));

        AndroidNetworking.post(ObtenerURL(CompartirOferta))
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(articulo)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(Result.class, new ParsedRequestListener<Result<Integer>>(){
                    @Override
                    public void onResponse(Result<Integer> response) {
                        if(response != null && response.getExito())
                        {
                            final Result<Integer> finalResponse = response;
                            EnviarImagen(articulo, response.getValor(),  new INetAction<String>() {
                                @Override
                                public void Execute(String entidad) {
                                    OnResponse.Execute(finalResponse.getValor());
                                    CerrarMenajeProgreso();
                                }
                            });
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
                        Toast.makeText(ctx, anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void EnviarImagen(Articulo articulo, Integer id_oferta, final INetAction<String> OnResponse) {
        PackageManager m = ctx.getPackageManager();
        String s = ctx.getPackageName();
        PackageInfo p = null;
        try {
            p = m.getPackageInfo(s, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        s = p.applicationInfo.dataDir + "/files/";

        File imagen = new File(s + articulo.getImage_path());

        AndroidNetworking.upload(ObtenerURL(EnviarImagen))
                .addMultipartFile("Imagen", imagen)
                .addMultipartParameter("id_oferta", ""  + id_oferta)
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                    }
                })
                .getAsObject(Result.class, new ParsedRequestListener<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        OnResponse.Execute(response.getValor());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ctx, "Error al subir la imagen", Toast.LENGTH_SHORT).show();
                    }
                });



    }
}
