package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Erick on 07/07/2015.
 */
public class FeedBack extends ActionBarActivity implements View.OnClickListener{

    private EditText conteudo;
    private Button btCancelar, btEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_feedback);
        actionBarSetup();

        conteudo = (EditText)findViewById(R.id.editTextConteudo);
        btCancelar = (Button)findViewById(R.id.buttonCancelar);
        btEnviar = (Button)findViewById(R.id.buttonEnviar);

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

        if(v.getId() == R.id.buttonEnviar){

        }

        if(v.getId() == R.id.buttonCancelar){
            finish();
        }
    }
}
