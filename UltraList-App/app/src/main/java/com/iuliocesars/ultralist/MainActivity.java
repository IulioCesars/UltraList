package com.iuliocesars.ultralist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iuliocesars.ultralist.Activity.ListaActivity;
import com.iuliocesars.ultralist.Adaptadores.ListaAdapter;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.DAO.DAO;
import com.iuliocesars.ultralist.Fragmentos.MainFragment;
import com.iuliocesars.ultralist.Fragmentos.MapaOfertasFragment;
import com.iuliocesars.ultralist.Fragmentos.OfertasFragment;
import com.iuliocesars.ultralist.Interfaces.IFragment;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    IFragment fragmentoActual;

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

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        changeFragment(new MainFragment(), RequestCode.MainFragment);
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
            case R.id.nav_listas : { changeFragment(new MainFragment(), RequestCode.MainFragment); break;}
            case R.id.nav_ofertas : {changeFragment(new OfertasFragment(), RequestCode.OfertasFragment); break;}
            default: { break; }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(fragmentoActual!=null)
        { fragmentoActual.onActivityResult(requestCode, resultCode, data); }
    }

    private void changeFragment(IFragment newFragment, int tag) { changeFragment(newFragment, Integer.toString(tag));}

    private void changeFragment(IFragment newFragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();

        fragmentoActual = (IFragment) fm.findFragmentByTag(tag);
        if(fragmentoActual != null && ((Fragment)fragmentoActual).isVisible())
            return;
        else
            fragmentoActual = newFragment;

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainFragmentContainer,(Fragment)fragmentoActual, tag);

        ft.commit();
    }
}