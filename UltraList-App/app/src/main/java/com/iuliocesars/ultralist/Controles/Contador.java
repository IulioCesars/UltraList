package com.iuliocesars.ultralist.Controles;

import android.app.Activity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iuliocesars.ultralist.R;

import java.math.BigDecimal;

import static android.text.InputType.*;

/**
 * Created by IulioCesars on 21/04/2018.
 */

public class Contador {
    private Button btnMas, btnMenos;
    private EditText etValor;
    private int valorMinimo, valorMaximo;
    private Activity c;


    public Contador(int id_btnMenos, int id_etValor, int id_btnMas, Activity context)
    {
        c = context;
        btnMenos = c.findViewById(id_btnMenos);
        etValor = c.findViewById(id_etValor);
        btnMas =  c.findViewById(id_btnMas);
        valorMinimo = 0;
        valorMaximo = -1;

        // Forzar el tipo
        //etValor.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        AsignarEventos();
    }


    private void AsignarEventos()
    {
        AsignarValor(0);

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsignarValor(ObtenerValor() - 1);
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsignarValor(ObtenerValor() + 1);
            }
        });
    }

    public void AsignarValor(int nuevoValor)
    {
        String mensaje = null;

        if(nuevoValor < valorMinimo && valorMinimo != -1)
        {
            nuevoValor = valorMinimo;
            mensaje = c.getResources().getString(R.string.txtValidacionValorMinimo) + valorMinimo;
        }

        if(nuevoValor > valorMaximo && valorMaximo != -1)
        {
            nuevoValor = valorMaximo;
            mensaje = c.getResources().getString(R.string.txtValidacionValorMaximo) + valorMaximo;
        }

        etValor.setText(Integer.toString(nuevoValor));

        /*
        if(mensaje != null)
        {
            Toast.makeText(c, mensaje, Toast.LENGTH_SHORT).show();
        }
        */

    }

    public void AsignarValor(BigDecimal nuevoValor)
    {
        String mensaje = null;
        BigDecimal bValorMinimo, bValorMaximo;
        bValorMinimo = new BigDecimal(valorMinimo);
        bValorMaximo = new BigDecimal(valorMaximo);

        if(nuevoValor.doubleValue() < bValorMinimo.doubleValue() && valorMinimo != -1)
        {
            nuevoValor = bValorMinimo;
            mensaje = c.getResources().getString(R.string.txtValidacionValorMinimo) + valorMinimo;
        }

        if(nuevoValor.doubleValue() > bValorMaximo.doubleValue() && valorMaximo != -1)
        {
            nuevoValor = bValorMaximo;
            mensaje = c.getResources().getString(R.string.txtValidacionValorMaximo) + valorMaximo;
        }

        etValor.setText(nuevoValor.toString());

        /*
        if(mensaje != null)
        {
            Toast.makeText(c, mensaje, Toast.LENGTH_SHORT).show();
        }
        */

    }

    public int ObtenerValor()
    {
        String sValor = etValor.getText().toString();
        return Integer.parseInt(sValor);
    }

    public BigDecimal ObtenerValorDecimal(){
        String sValor = etValor.getText().toString();
        BigDecimal bdValor = new BigDecimal(sValor);

        return bdValor;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setVisibility(boolean valor)
    {
        int visible = valor ? View.VISIBLE : View.INVISIBLE;
        btnMas.setVisibility(visible);
        btnMenos.setVisibility(visible);
        etValor.setVisibility(visible);
    }
}
