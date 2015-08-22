package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageButton;

/**
 * Created by JE on 21/08/2015.
 */
public class Desenvolvedores extends ActionBarActivity implements View.OnClickListener{

    private ImageButton imagemBtErico, imagemBtDaniel, imagemBtFelipe, imagemBtGeo, imagemBtRaissa, imagemBtRamon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_desenvolvedores);
        actionBarSetup();

        imagemBtErico = (ImageButton)findViewById(R.id.imageButtonErico);
        imagemBtErico.setOnClickListener(this);

        imagemBtDaniel = (ImageButton)findViewById(R.id.imageButtonDaniel);
        imagemBtDaniel.setOnClickListener(this);

        imagemBtFelipe = (ImageButton)findViewById(R.id.imageButtonFelipe);
        imagemBtFelipe.setOnClickListener(this);

        imagemBtGeo = (ImageButton)findViewById(R.id.imageButtonGeo);
        imagemBtGeo.setOnClickListener(this);

        imagemBtRaissa = (ImageButton)findViewById(R.id.imageButtonRaissa);
        imagemBtRaissa.setOnClickListener(this);

        imagemBtRamon = (ImageButton)findViewById(R.id.imageButtonRamon);
        imagemBtRamon.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SpannableString s = new SpannableString("  SDIH");
            s.setSpan(new TypefaceSpan(this, "Aero.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle(s);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.imageButtonErico){
            Intent telaWebView = new Intent(this, WebViewDesenvolvedor.class);
            telaWebView.putExtra("Chave", "Erico");
            startActivity(telaWebView);

        }

        if(v.getId() == R.id.imageButtonDaniel){

            Intent telaWebView = new Intent(this, WebViewDesenvolvedor.class);
            telaWebView.putExtra("Chave", "Daniel");
            startActivity(telaWebView);

        }

        if(v.getId() == R.id.imageButtonFelipe){

            Intent telaWebView = new Intent(this, WebViewDesenvolvedor.class);
            telaWebView.putExtra("Chave", "Felipe");
            startActivity(telaWebView);

        }

        if(v.getId() == R.id.imageButtonGeo){

            Intent telaWebView = new Intent(this, WebViewDesenvolvedor.class);
            telaWebView.putExtra("Chave", "Geo");
            startActivity(telaWebView);

        }

        if(v.getId() == R.id.imageButtonRaissa){

            Intent telaWebView = new Intent(this, WebViewDesenvolvedor.class);
            telaWebView.putExtra("Chave", "Raissa");
            startActivity(telaWebView);

        }

        if(v.getId() == R.id.imageButtonRamon){

            Intent telaWebView = new Intent(this, WebViewDesenvolvedor.class);
            telaWebView.putExtra("Chave", "Ramon");
            startActivity(telaWebView);

        }
    }
}
