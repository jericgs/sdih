package com.example.erick.myapplicationsdh10;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends Activity implements View.OnClickListener, View.OnKeyListener{

    private EditText caixa1=null, caixa2= null;
    private Button botao1;
    private String respostaRetornada = null;
    private ProgressDialog pd;
    private Handler handler = new Handler();
    private boolean logico = false;
    private Intent telaMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        caixa1 = (EditText)findViewById(R.id.editText1);
        caixa1.setOnKeyListener(this);

        caixa2 = (EditText)findViewById(R.id.editText2);
        caixa2.setOnKeyListener(this);

        botao1 = (Button)findViewById(R.id.button1);
        botao1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if((v.getId() == R.id.button1)){

            pd = new ProgressDialog(this);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);

            pd.show();

            telaMenu = new Intent(this, Menu.class);

            new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        Thread.sleep(5900);
                        pd.dismiss();
                        startActivity(telaMenu);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }).start();
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