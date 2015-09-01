package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by Erick on 21/06/2015.
 */
public class Menu extends ActionBarActivity implements View.OnClickListener{

    private ImageButton imagemBt1, imagemBt2, imagemBt3, imagemBt4;
    private AlertDialog alerta;
    public static final int REQUEST_CODE = 0;
    private String respostaRetornadaQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu);
        actionBarSetup();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        imagemBt1 = (ImageButton)findViewById(R.id.imageButton1);
        imagemBt1.setOnClickListener(this);

        imagemBt2 = (ImageButton)findViewById(R.id.imageButton2);
        imagemBt2.setOnClickListener(this);

        imagemBt3 = (ImageButton)findViewById(R.id.imageButton3);
        imagemBt3.setOnClickListener(this);

        imagemBt4 = (ImageButton)findViewById(R.id.imageButton4);
        imagemBt4.setOnClickListener(this);
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
    public void onBackPressed(){

        int layout = R.layout.tela_alerta_voltar;
        int idButtonSim = R.id.buttonSim;
        int idButtonNao = R.id.buttonNao;
        alertaDialogo(layout, idButtonSim, idButtonNao);
        Log.i("onBackPressed()", "Passou");
    }

    public void alertaDialogo(int layout, int idButtonSim, int idButtonNao){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layout, null);
        view.findViewById(idButtonSim).setOnClickListener(this);
        view.findViewById(idButtonNao).setOnClickListener(this);
        builder.setCustomTitle(view);
        alerta = builder.create();
        alerta.setCancelable(false);
        alerta.setCanceledOnTouchOutside(false);
        alerta.show();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1_desenvolvedores) {
            Intent telaDesenvolvedores = new Intent(this, Desenvolvedores.class);
            startActivity(telaDesenvolvedores);
            return true;
        }

        if (id == R.id.item2_feedback) {
            Intent telaFeedBack = new Intent(this, FeedBack.class);
            startActivity(telaFeedBack);
            return true;
        }

        if (id == R.id.item3_Sobre) {
            Intent telaSobre = new Intent(this, Sobre.class);
            startActivity(telaSobre);
            return true;
        }

        if(id == R.id.item4_sair){
            Intent telaLogin = new Intent(this, Login.class);
            startActivity(telaLogin);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.imageButton1){

            Intent telaScanner = new Intent(this, com.google.zxing.client.android.CaptureActivity.class);
            startActivityForResult(telaScanner, REQUEST_CODE);
        }

        if(v.getId() == R.id.imageButton2){

            Intent telaAdicionarVaga = new Intent(this, AdicionarVagaPeloGPS.class);
            startActivity(telaAdicionarVaga);
        }

        if(v.getId() == R.id.imageButton3){
            Intent telaMapa = new Intent(this, Mapa.class);
            startActivity(telaMapa);
        }

        if(v.getId() == R.id.imageButton4){

            Intent telaBuscarVaga = new Intent(this, BucarVaga.class);
            startActivity(telaBuscarVaga);
        }

        if(v.getId() == R.id.buttonSim){
            finish();
            alerta.dismiss();
        }

        if(v.getId() == R.id.buttonNao){
            alerta.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            //Toast.makeText(this, "RESULTADO: "+data.getStringExtra("SCAN_RESULT")+" ("+data.getStringExtra("SCAN_FORMAT")+")", Toast.LENGTH_LONG).show();

            ArrayList<NameValuePair> parametrosPostEnviarIdQRCode = new ArrayList<NameValuePair>();
            parametrosPostEnviarIdQRCode.add(new BasicNameValuePair("idCode",  data.getStringExtra("SCAN_RESULT")));

            try {
                respostaRetornadaQRcode = ConexaoHttpClient.execultaHttpPost(ConexaoHttpClient.enviarIdQRCode, parametrosPostEnviarIdQRCode);
                String respostaQRcode = respostaRetornadaQRcode.substring(4);
                Log.i("Informação Usuario 1: ", respostaQRcode);

                if(respostaQRcode.contains("~")){

                    String retornoQRcode [];
                    retornoQRcode = respostaQRcode.split("@");

                    for(int i = 0; i < retornoQRcode.length; i++){
                        Log.i("Retorno" + i + " : ", retornoQRcode[i]);
                    }

                    String informacaoCondutor [];
                    informacaoCondutor = retornoQRcode[0].split("~");

                    for(int i = 0; i < informacaoCondutor.length; i++){
                        Log.i("Retorno" + i + " : ", informacaoCondutor[i]);
                    }

                    String informacaoEndereco [];
                    informacaoEndereco = retornoQRcode[1].split("~");

                    for(int i = 0; i < informacaoEndereco.length; i++){
                        Log.i("Retorno" + i + " : ", informacaoEndereco[i]);
                    }

                    Intent telaRetornoQrcode = new Intent(this, RetornoQrCode.class);
                    telaRetornoQrcode.putExtra("informacaoCondutor", informacaoCondutor);
                    telaRetornoQrcode.putExtra("informacaoEndereco", informacaoEndereco);
                    startActivity(telaRetornoQrcode);

                }else{
                    ToastManager.show(this, "A busca não foi realizada com sucesso.", ToastManager.INFORMACOES);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            ToastManager.show(this, " A leitura não foi realizada com sucesso.", ToastManager.INFORMACOES);
        }
    }
}
