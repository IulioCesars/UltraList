package com.iuliocesars.ultralist.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuliocesars.ultralist.Activity.ListaActivity;
import com.iuliocesars.ultralist.Adaptadores.ArticuloAdapter;
import com.iuliocesars.ultralist.DAO.DAO;
import com.iuliocesars.ultralist.Interfaces.IFragment;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IulioCesars on 09/05/2018.
 */

public class OfertasFragment extends Fragment implements IFragment
{
    RecyclerView rvArticulos;
    List<Articulo> lstArticulos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vistaInflada = inflater.inflate(R.layout.fragment_main, container, false);

        return vistaInflada;
    }

    private void CargarLista()
    {
        lstArticulos = DAO.Articulo(getActivity()).ObtenerOfertas(); //new ArticuloDAO(this).ObtenerTodo();

        ArticuloAdapter aa = new ArticuloAdapter(lstArticulos);
        rvArticulos.setAdapter(aa);
        rvArticulos.setLayoutManager(
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvArticulos.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
