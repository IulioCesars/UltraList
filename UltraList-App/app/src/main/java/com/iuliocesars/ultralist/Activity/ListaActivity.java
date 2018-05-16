package com.iuliocesars.ultralist.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iuliocesars.ultralist.Adaptadores.ArticuloAdapter;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.DAO.DAO;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.Mensajes;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.math.BigDecimal;
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
    protected int DefinirMenu() { return R.menu.menu_lista; }

    @Override
    protected void IniciarViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvArticulos = findViewById(R.id.rvArticulos);
        etNombreLista = findViewById(R.id.etNombreLista);
        tvTotal = findViewById(R.id.tvTotal);



        setSupportActionBar(toolbar);
    }

    @Override
    protected void IniciarEventos() {
        super.IniciarEventos();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaActivity.this, ArticuloScrollingActivity.class);
                i.putExtra(Extras.fk_Lista, lista.getId_lista());
                startActivityForResult(i, RequestCode.ArticuloActivity);
            }
        });

        etNombreLista.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                lista.setNombre(editable.toString());
                DAO.Lista(ListaActivity.this).Modificar(lista);
            }
        });
    }

    @Override
    protected void CargarRegistro()
    {
        Intent i = getIntent();
        if (i.hasExtra(Extras.Lista))
        {
            lista = (Lista) i.getSerializableExtra(Extras.Lista);
        }
        else
        {
            lista = new Lista("Nueva Lista", "");
            DAO.Lista(this).Agregar(lista);
        }

        etNombreLista.setText(lista.getNombre());
        tvTotal.setText(String.format("Total: %f", 0.00));

        rvArticulos.requestFocus();
        CargarArticulos();
    }

    private void CargarArticulos()
    {
        lstArticulos = DAO.Articulo(this).ObtenerLista(lista.getId_lista()); //new ArticuloDAO(this).ObtenerTodo();

        ArticuloAdapter aa = new ArticuloAdapter(lstArticulos);
        rvArticulos.setAdapter(aa);
        rvArticulos.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvArticulos.setItemAnimator(new DefaultItemAnimator());

        BigDecimal total = new BigDecimal(0);

        for ( Articulo a : lstArticulos)
        { total = total.add(a.getTotal()); }

        tvTotal.setText(String.format("Total: %s", total.toString()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case RequestCode.ArticuloActivity:
                {
                    if(resultCode == RESULT_OK)
                    { }
                    break;
                }

        }
        CargarArticulos();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.m_eliminar: { EliminarRegistro(); break; }
        }

        return super.onOptionsItemSelected(item);
    }

    public void EliminarRegistro()
    {
        Mensajes.Confirmar(this, R.string.txtEliminarRegistro, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(DAO.Lista(ListaActivity.this).Eliminar(lista))
                    finish();
            }
        });
    }
}
