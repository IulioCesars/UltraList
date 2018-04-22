package com.iuliocesars.ultralist.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by IulioCesars on 11/03/2018.
 */

public class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(DefinirLayout());
            IniciarViews();
            IniciarEventos();
            CargarRegistro();

        }catch (Exception ex)
        {
            int i=0;
        }
    }

    protected int DefinirLayout()
    {
        return  0;
    }

    protected void IniciarViews() { }

    protected void IniciarEventos() { }

    protected void CargarRegistro() { }

    protected void GuardarRegistro() {}
}
