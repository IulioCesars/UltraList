package com.iuliocesars.ultralist.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iuliocesars.ultralist.Adaptadores.OfertaAdapter;
import com.iuliocesars.ultralist.Interfaces.IFragment;
import com.iuliocesars.ultralist.MainActivity;
import com.iuliocesars.ultralist.Modelos.Oferta;
import com.iuliocesars.ultralist.NET.INetAction;
import com.iuliocesars.ultralist.NET.INetResponse;
import com.iuliocesars.ultralist.NET.Net;
import com.iuliocesars.ultralist.R;

import java.util.List;

/**
 * Created by IulioCesars on 09/05/2018.
 */

public class OfertasFragment extends Fragment implements IFragment
{
    RecyclerView rvOfertas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vistaInflada = inflater.inflate(R.layout.fragment_ofertas, container, false);

        rvOfertas = vistaInflada.findViewById(R.id.rvOfertas);

        CargarLista();
        return vistaInflada;
    }

    private void CargarLista()
    {
        setBloquearShake(true);

        Net.Oferta(getActivity()).ObtenerLista(new INetResponse<List<Oferta>>() {
            @Override
            public void OnSuccess(List<Oferta> entidad) {
                OfertaAdapter oo = new OfertaAdapter(entidad);
                rvOfertas.setAdapter(oo);
                rvOfertas.setLayoutManager(
                        new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                rvOfertas.setItemAnimator(new DefaultItemAnimator());
                setBloquearShake(false);
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(OfertasFragment.this.getActivity(), msg, Toast.LENGTH_SHORT).show();
                setBloquearShake(false);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void setBloquearShake(boolean valor)
    { ((MainActivity)getActivity()).bloquearShake = valor; }
}
