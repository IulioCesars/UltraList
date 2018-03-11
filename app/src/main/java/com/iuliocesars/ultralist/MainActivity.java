package com.iuliocesars.ultralist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iuliocesars.ultralist.Activity.ListaActivity;
import com.iuliocesars.ultralist.Adaptadores.ListaAdapter;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fabAgregarLista;
    RecyclerView rvListas;
    List<Lista> lstListas;
    ListaAdapter la;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int DefinirLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void IniciarViews()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fabAgregarLista = (FloatingActionButton) findViewById(R.id.fab);
        rvListas = findViewById(R.id.rvListas);
        rvListas.setHasFixedSize(true);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        IniciarListas();
    }
    @Override
    protected void IniciarEventos()
    {
        fabAgregarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                lstListas.add(new Lista("Nombre", "Descripcion"));
                la.notifyItemInserted(0);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                Intent i = new Intent(MainActivity.this, ListaActivity.class);
                startActivityForResult(i, RequestCode.ListaActivity);
            }
        });

        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado el elemento " + rvListas.getChildPosition(view), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IniciarListas()
    {
        lstListas = new ArrayList<>();
        lstListas.add(new Lista("Nombre", "Descripcion"));
        lstListas.add(new Lista("Nombre", "Descripcion"));
        lstListas.add(new Lista("Nombre", "Descripcion"));
        lstListas.add(new Lista("Nombre", "Descripcion"));
        lstListas.add(new Lista("Nombre", "Descripcion"));
        lstListas.add(new Lista("Nombre", "Descripcion"));
        la = new ListaAdapter(lstListas);
        rvListas.setAdapter(la);
        rvListas.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvListas.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            default: { break; }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            default: { break; }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case RequestCode.MainActivity: { break; }
            default: { break; }
        }
    }
}