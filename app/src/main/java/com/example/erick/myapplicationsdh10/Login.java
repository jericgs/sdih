package com.example.erick.myapplicationsdh10;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Login extends Activity implements View.OnClickListener, View.OnKeyListener{

    private EditText caixa1=null, caixa2= null;
    private Button botao1;
    private String respostaRetornada;
    private ProgressDialog pd;
    private Handler handler = new Handler();
    private boolean logico = false;
    private Intent telaMenu;
    private DBAdapter banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        banco = new DBAdapter(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        caixa1 = (EditText)findViewById(R.id.editText1);
        caixa1.setOnKeyListener(this);

        caixa2 = (EditText)findViewById(R.id.editText2);
        caixa2.setOnKeyListener(this);

        botao1 = (Button)findViewById(R.id.button1);
        botao1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if((v.getId() == R.id.button1)) {

            pd = new ProgressDialog(this);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);

            pd.show();

            telaMenu = new Intent(this, Menu.class);

            if (caixa1.getText().toString().equals("") || caixa2.getText().toString().equals("")) {
                ToastManager.show(this, "Preencha todos os campos", ToastManager.INFORMACOES);
                pd.dismiss();
            }
            else {

                ArrayList<NameValuePair> parametroParaLoginUsuario = new ArrayList<NameValuePair>();

                parametroParaLoginUsuario.add(new BasicNameValuePair("login", caixa1.getText().toString()));
                parametroParaLoginUsuario.add(new BasicNameValuePair("senha", caixa2.getText().toString()));

                try {

                    respostaRetornada = ConexaoHttpClient.execultaHttpPost(ConexaoHttpClient.enviarSolicitacaoLogin, parametroParaLoginUsuario);
                    Log.i("informação", respostaRetornada);

                    if(respostaRetornada.toString().contains("1")){

                        banco.abrir();
                        banco.insertTabelaPersistencia("1", "true");
                        banco.fechar();

                        new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    Thread.sleep(1000);
                                    pd.dismiss();
                                    startActivity(telaMenu);
                                    finish();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    pd.dismiss();
                                }
                            }

                        }).start();

                    }else{
                        pd.dismiss();
                        ToastManager.show(this, "Usuário ou senha incorreto", ToastManager.INFORMACOES);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                if(v.getId() == R.id.editText1){
                    Log.i("INFORMÇAO", "Caixa 1");
                    caixa2.requestFocus();
                    return true;
                }

                return false;
            }
            return false;
        }
        return false;
    }
}
