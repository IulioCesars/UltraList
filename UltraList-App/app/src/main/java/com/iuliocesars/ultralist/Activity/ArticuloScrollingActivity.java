package com.iuliocesars.ultralist.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.Controles.Contador;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.R;

public class ArticuloScrollingActivity extends BaseActivity {
    Toolbar toolbar;
    FloatingActionButton fabTomarFoto, fabGuardar;
    EditText etNombre, etDescripcion;
    Spinner spCategoria;
    Contador contadorCantidad, contadorPrecioUnitario;
    Articulo articulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int DefinirLayout() { return R.layout.activity_acticulo_scrolling; }

    @Override
    protected void IniciarViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabTomarFoto = (FloatingActionButton) findViewById(R.id.btnTomarFoto);
        fabGuardar = findViewById(R.id.fabGuardar);
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        spCategoria = findViewById(R.id.spCategoria);
        contadorCantidad = new Contador(R.id.btnMenosCantidad, R.id.etCantidad, R.id.btnMasCantidad, this);
        contadorPrecioUnitario = new Contador(R.id.btnMenosPrecioUnitario, R.id.etPrecioUnitario, R.id.btnMasPrecioUnitario, this);
    }

    @Override
    protected void IniciarEventos() {
        fabTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TomarFoto();
            }
        });

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarRegistro();
            }
        });
    }

    private void TomarFoto()
    {

    }

    @Override
    protected void CargarRegistro() {

    }

    @Override
    protected void GuardarRegistro() {
        super.GuardarRegistro();
    }

}
