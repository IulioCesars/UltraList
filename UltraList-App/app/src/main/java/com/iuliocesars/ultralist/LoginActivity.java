package com.iuliocesars.ultralist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.iuliocesars.ultralist.NET.ArticuloNet;
import com.iuliocesars.ultralist.Util.ConexionWS;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.RequestCode;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    TextView tvTitulo;
    LoginButton loginButton;
    Button btnOmitir;
    boolean cheatLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cheatLogin = false;
        tvTitulo = findViewById(R.id.tvTitulo);
        callbackManager = CallbackManager.Factory.create();

        tvTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cheatLogin)
                {
                    IniciarApp();
                }
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn)
        {
            IniciarApp();
        }
        
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        IniciarSesion(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected void IniciarSesion(LoginResult loginResult){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted( JSONObject object, GraphResponse response) {
                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            String photo = "https://graph.facebook.com/" + id + "/picture?type=large";

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(Extras.Name, name);
                            editor.putString(Extras.ID, id);
                            editor.putString(Extras.Photo, photo);

                            editor.apply();
                            IniciarApp();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        request.executeAsync();
    }

    protected void IniciarApp()
    {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivityForResult(i, RequestCode.MainActivity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cheatLogin = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
