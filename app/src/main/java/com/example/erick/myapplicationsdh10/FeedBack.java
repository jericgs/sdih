package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        conteudo = (EditText)findViewById(R.id.editTextConteudo);
        btCancelar = (Button)findViewById(R.id.buttonCancelar);
        btCancelar.setOnClickListener(this);
        btEnviar = (Button)findViewById(R.id.buttonEnviar);
        btEnviar.setOnClickListener(this);

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
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu_botaovoltar, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonEnviar){

            if(v.getId() == R.id.buttonEnviar){

                Log.i("Informação", "retorno: " + verificaNet.verificaInternet( this.getApplicationContext() ));

                if( verificaNet.verificaInternet(this.getApplicationContext())){

                    try {
                        GMailSender mail = new GMailSender("sdihdevfive@gmail.com", "sleepsdih@@");
                        mail.sendMail("SDIH - FeedBack", conteudo.getText().toString(), "sdihdevfive@gmail.com", "devfive@googlegroups.com");
                        Log.i("Informação", "Try");
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ToastManager.show(this, "Verifique sua Conexão.", ToastManager.INFORMACOES);
                    finish();
                }
            }
        }

        if(v.getId() == R.id.buttonCancelar){
            finish();
        }
    }
}
