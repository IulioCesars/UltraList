package com.iuliocesars.ultralist.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by IulioCesars on 11/03/2018.
 */

public class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(DefinirLayout());
        IniciarViews();
        IniciarEventos();
        CargarRegistro();
    }

    protected int DefinirLayout()
    {
        return  0;
    }

    protected void IniciarViews() { }

    protected void IniciarEventos() { }

    protected void CargarRegistro() { }

    protected void GuardarRegistro() {}

    protected int DefinirMenu(){ return 0; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int idMenu = DefinirMenu();
        if(idMenu!=0)
        { getMenuInflater().inflate(idMenu, menu); }

        return super.onCreateOptionsMenu(menu);
    }
}
