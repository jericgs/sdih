package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Erick on 07/07/2015.
 */
public class FeedBack extends ActionBarActivity implements View.OnClickListener{

    private EditText conteudo;
    private Button btCancelar, btEnviar;
    private ProgressDialog pd;
    private Handler mHandler;


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
            pd = new ProgressDialog(this);
            pd.setMessage("Enviando...");
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    if(enviarEmail()){
                        pd.dismiss();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                ToastManager.show(FeedBack.this, "E-mail enviado com sucesso", ToastManager.INFORMACOES);
                                finish();
                            }
                        });

                    }else{

                        pd.dismiss();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                ToastManager.show(FeedBack.this, "Não possível enviar o e-mail. Verifique sua conexão", ToastManager.INFORMACOES);
                                finish();
                            }
                        });
                    }
                }
            }).start();

        }

        if(v.getId() == R.id.buttonCancelar){
            finish();
        }

    }

    public boolean enviarEmail(){

        Log.i("Informação", "retorno: " + verificaNet.verificaInternet( this.getApplicationContext() ));

        if( verificaNet.verificaInternet(this.getApplicationContext())){

            try {

                GMailSender mail = new GMailSender("sdihdevfive@gmail.com", "sleepsdih@@");
                mail.sendMail("SDIH - FeedBack", conteudo.getText().toString(), "sdihdevfive@gmail.com", "devfive@googlegroups.com");
                Log.i("Informação", "Try");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }else{
            return false;
        }
    }
}
