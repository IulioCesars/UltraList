package com.iuliocesars.ultralist.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iuliocesars.ultralist.Fragmentos.MapaOfertasFragment;
import com.iuliocesars.ultralist.Interfaces.IFragment;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.RequestCode;

public class MapsActivity extends AppCompatActivity {
    IFragment fragmentoActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        changeFragment(new MapaOfertasFragment(), RequestCode.MapaOfertasFragment);
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
