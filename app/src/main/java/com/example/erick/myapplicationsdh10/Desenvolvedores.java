package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
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

        imagemBtRaissa = (ImageButton)findViewById(R.id.imageButtonRamon);
        imagemBtRaissa.setOnClickListener(this);

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

        }

        if(v.getId() == R.id.imageButtonDaniel){

        }

        if(v.getId() == R.id.imageButtonFelipe){

        }

        if(v.getId() == R.id.imageButtonGeo){

        }

        if(v.getId() == R.id.imageButtonRaissa){

        }

        if(v.getId() == R.id.imageButtonRamon){

        }
    }
}
