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
import android.widget.Toast;

import com.iuliocesars.ultralist.Activity.ListaActivity;
import com.iuliocesars.ultralist.Adaptadores.ListaAdapter;
import com.iuliocesars.ultralist.DAO.DAO;
import com.iuliocesars.ultralist.Interfaces.IFragment;
import com.iuliocesars.ultralist.MainActivity;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.util.List;

/**
 * Created by IulioCesars on 06/05/2018.
 */

public class MainFragment extends Fragment implements IFragment{

    FloatingActionButton fabAgregarLista;
    RecyclerView rvListas;
    List<Lista> lstListas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vistaInflada = inflater.inflate(R.layout.fragment_main, container, false);

        fabAgregarLista = (FloatingActionButton) vistaInflada.findViewById(R.id.fab);
        rvListas = vistaInflada.findViewById(R.id.rvListas);
        rvListas.setHasFixedSize(true);

        fabAgregarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), ListaActivity.class);
                startActivityForResult(i, RequestCode.ListaActivity);
            }
        });

        CargarListas();

        return vistaInflada;
    }

    private void CargarListas()
    {
        lstListas = DAO.Lista(getContext()).ObtenerTodo();
        ListaAdapter la = new ListaAdapter(lstListas);
        rvListas.setAdapter(la);
        rvListas.setLayoutManager(
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rvListas.setItemAnimator(new DefaultItemAnimator());
    }

    // Falta poner la logica para hacer el on activity result del fragmetno desde el activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case RequestCode.MainActivity: { break; }
            case RequestCode.ListaActivity: {

                break;
            }
            default: { break; }
        }
        CargarListas();
    }
}
