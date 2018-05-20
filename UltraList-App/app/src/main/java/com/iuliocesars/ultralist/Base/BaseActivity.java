package com.iuliocesars.ultralist.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.iuliocesars.ultralist.Sensor.ShakeDetector;

/**
 * Created by IulioCesars on 11/03/2018.
 */

public class BaseActivity extends AppCompatActivity
{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(DefinirLayout());
        IniciarViews();
        IniciarEventos();
        CargarRegistro();

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				OnShake();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
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

    protected void OnShake() {
        Toast.makeText(BaseActivity.this, "SHAKE", Toast.LENGTH_SHORT).show();
    }
}
