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
import android.widget.EditText;
import android.widget.TextView;

import com.iuliocesars.ultralist.Adaptadores.ArticuloAdapter;
import com.iuliocesars.ultralist.Adaptadores.ListaAdapter;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends BaseActivity{

    RecyclerView rvArticulos;
    FloatingActionButton fab;
    Toolbar toolbar;
    Lista lista;
    List<Articulo> lstArticulos;
    EditText etNombreLista;
    TextView tvTotal;

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
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvArticulos = findViewById(R.id.rvArticulos);
        etNombreLista = findViewById(R.id.etNombreLista);
        tvTotal = findViewById(R.id.tvTotal);

        setSupportActionBar(toolbar);
        CargarArticulo();
    }

    private void CargarArticulo()
    {
        Intent i = getIntent();
        if (i.hasExtra(Extras.Lista))
        {
            lista = (Lista) i.getSerializableExtra(Extras.Lista);
        }
        else
        {
            lista = new Lista("Nueva Lista", "");
            lstArticulos = new ArrayList<>();
        }

        etNombreLista.setText(lista.getNombre());
        tvTotal.setText(String.format("Total: %f", 0.00));

        rvArticulos.requestFocus();
        CargarArticulos();
    }

    private void CargarArticulos()
    {
        lstArticulos = new ArrayList<>();
        lstArticulos.add(new Articulo("Nombre", "Desc", "Categoria", new BigDecimal(15)));
        lstArticulos.add(new Articulo("Nombre", "Desc", "Categoria", new BigDecimal(15)));
        lstArticulos.add(new Articulo("Nombre", "Desc", "Categoria", new BigDecimal(15)));
        lstArticulos.add(new Articulo("Nombre", "Desc", "Categoria", new BigDecimal(15)));
        lstArticulos.add(new Articulo("Nombre", "Desc", "Categoria", new BigDecimal(15)));
        lstArticulos.add(new Articulo("Nombre", "Desc", "Categoria", new BigDecimal(15)));

        ArticuloAdapter aa = new ArticuloAdapter(lstArticulos);
        rvArticulos.setAdapter(aa);
        rvArticulos.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvArticulos.setItemAnimator(new DefaultItemAnimator());
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
