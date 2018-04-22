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
import com.iuliocesars.ultralist.DAO.ArticuloDAO;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.RequestCode;

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
        lstArticulos = new ArticuloDAO(this).ObtenerTodo();

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
                Intent i = new Intent(ListaActivity.this, ArticuloScrollingActivity.class);
                i.putExtra(Extras.fk_Lista, 0);
                startActivityForResult(i, RequestCode.ArticuloActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case RequestCode.ArticuloActivity:
                {
                    if(resultCode == RESULT_OK)
                    { CargarArticulos(); }
                    break;
                }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
