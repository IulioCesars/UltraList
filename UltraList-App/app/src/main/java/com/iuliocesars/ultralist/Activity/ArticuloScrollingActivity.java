package com.iuliocesars.ultralist.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.Controles.Contador;
import com.iuliocesars.ultralist.DAO.ArticuloDAO;
import com.iuliocesars.ultralist.DAO.DAO;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Oferta;
import com.iuliocesars.ultralist.NET.INetAction;
import com.iuliocesars.ultralist.NET.Net;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.Maps;
import com.iuliocesars.ultralist.Util.Mensajes;
import com.iuliocesars.ultralist.Util.Result;
import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;

public class ArticuloScrollingActivity extends BaseActivity {
    Toolbar toolbar;
    FloatingActionButton fabTomarFoto, fabGuardar;
    EditText etNombre, etDescripcion;
    Spinner spCategoria;
    Contador contadorCantidad, contadorPrecioUnitario;
    ImageView ivFotoArticulo;
    Articulo articulo;
    Boolean modoEdicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int DefinirLayout() { return R.layout.activity_acticulo_scrolling; }

    @Override
    protected int DefinirMenu() { return R.menu.menu_acticulo_scrolling; }

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
        ivFotoArticulo = findViewById(R.id.ivFotoArticulo);
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
                GuardarRegistro(false);
            }
        });
    }

    private void TomarFoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void CargarRegistro() {
        Intent i = getIntent();

        if(i.hasExtra(Extras.Articulo))
        {
            articulo = (Articulo) i.getSerializableExtra(Extras.Articulo);

            etNombre.setText(articulo.getNombre());
            etDescripcion.setText(articulo.getDescripcion());
            spCategoria.setSelection(0);
            contadorCantidad.AsignarValor(articulo.getCantidad());
            contadorPrecioUnitario.AsignarValor(articulo.getPrecio());

            if(articulo.getImage_path() != null && !articulo.getImage_path().isEmpty())
            {
                try
                {

                    //Picasso.get().load(articulo.getImage_path()).into(ivFotoArticulo);
                    FileInputStream fis = openFileInput(articulo.getImage_path());
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    ivFotoArticulo.setImageBitmap(bitmap);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            modoEdicion = true;
        }
        else if(i.hasExtra(Extras.fk_Lista))
        {
            articulo = new Articulo();
            articulo.setFk_lista(i.getIntExtra(Extras.fk_Lista, 0));
            modoEdicion = false;
        }
        else
        {
            finish();
        }
    }

    protected void GuardarRegistro(boolean es_oferta) {
        Articulo articulo = ObtenerArticulo();
        articulo.setEs_oferta(es_oferta);

        if(es_oferta) {
            LatLng l = Maps.getCurrentLocation(this);
            if(l != null)
            { articulo.SetUbicacion(l); }

            Net.Articulo(this).CompartirOferta(articulo, new INetAction<Integer>() {
                @Override
                public void Execute(Integer entidad) {
                    Toast.makeText(ArticuloScrollingActivity.this, "Se compartio como oferta", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(!modoEdicion)
        {
            if (new ArticuloDAO(this).Agregar(articulo)) {


                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if (new ArticuloDAO(this).Modificar(articulo)) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            } else {
                Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void EliminarRegistro()
    {
        Mensajes.Confirmar(this, R.string.txtEliminarRegistro, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(DAO.Articulo(ArticuloScrollingActivity.this).Eliminar(articulo))
                    finish();
            }
        });
    }

    protected void CompartirOferta()
    {
        Mensajes.Confirmar(this, R.string.txtCompartirOferta, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GuardarRegistro(true);
            }
        });
    }

    private Articulo ObtenerArticulo()
    {
        //articulo.setFk_lista(0);
        articulo.setNombre(etNombre.getText().toString());
        articulo.setDescripcion(etDescripcion.getText().toString());
        articulo.setCategoria(spCategoria.getSelectedItem().toString());
        articulo.setCantidad(contadorCantidad.ObtenerValor());
        articulo.setPrecio(contadorPrecioUnitario.ObtenerValorDecimal());

        return  articulo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Leemos la imagen
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                // Guardamos el thumbnail de la imagen en un archivo con el siguiente nombre
                articulo.setImage_path("picture_" + new Date().getTime() + ".jpg");
                FileOutputStream fos = openFileOutput(articulo.getImage_path(), MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();

                ivFotoArticulo.setImageBitmap(bitmap);
            } catch(Exception ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Problema al regresar de la cámara", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.m_compartirOferta: { CompartirOferta(); break;}
            case R.id.m_eliminar_articulo: { EliminarRegistro(); break; }
        }
        return super.onOptionsItemSelected(item);
    }
}
