package com.iuliocesars.ultralist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.iuliocesars.ultralist.Adaptadores.ListaAdapter;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends BaseActivity{

    RecyclerView rvArticulos;
    FloatingActionButton fab;
    Toolbar toolbar;
    Lista lista;
    List<Articulo> lstArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int DefinirLayout() {
        return R.layout.activity_lista;
    }

    @Override
    protected void IniciarViews() {
        rvArticulos = findViewById(R.id.rvArticulos);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CargarArticulo();
    }

    private void CargarArticulo()
    {
        Intent i = getIntent();
        if (i.getExtras().size() > 0)
        {

        }
        else
        {
            lista = new Lista("Nueva Lista", "");
            lstArticulos = new ArrayList<>();
        }
        getSupportActionBar().setTitle(lista.getNombre());
    }

    private void IniciarArticulos()
    {

    }

    @Override
    protected void IniciarEventos() {
        super.IniciarEventos();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
