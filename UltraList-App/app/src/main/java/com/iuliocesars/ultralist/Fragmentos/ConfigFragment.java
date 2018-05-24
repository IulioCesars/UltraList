package com.iuliocesars.ultralist.Fragmentos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iuliocesars.ultralist.Adaptadores.OfertaAdapter;
import com.iuliocesars.ultralist.Interfaces.IFragment;
import com.iuliocesars.ultralist.MainActivity;
import com.iuliocesars.ultralist.Modelos.Oferta;
import com.iuliocesars.ultralist.NET.INetResponse;
import com.iuliocesars.ultralist.NET.Net;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.ConexionWS;
import com.iuliocesars.ultralist.Util.Extras;

import java.util.List;

/**
 * Created by IulioCesars on 22/05/2018.
 */

public class ConfigFragment extends Fragment implements IFragment
{
    EditText etWebService;
    Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View vistaInflada = inflater.inflate(R.layout.fragment_config, container, false);

        etWebService = vistaInflada.findViewById(R.id.etWebService);
        btnGuardar = vistaInflada.findViewById(R.id.btnGuardar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(vistaInflada.getContext());
        etWebService.setText(preferences.getString(Extras.WebService, ""));


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(vistaInflada.getContext());
                SharedPreferences.Editor editor = preferences.edit();
                ConexionWS.URL = etWebService.getText().toString();
                editor.putString(Extras.WebService, ConexionWS.URL);
                editor.apply();
                Toast.makeText(vistaInflada.getContext(), vistaInflada.getResources().getString(R.string.txtGuardando), Toast.LENGTH_SHORT).show();
            }
        });


        return vistaInflada;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void setBloquearShake(boolean valor)
    { ((MainActivity)getActivity()).bloquearShake = valor; }
}
