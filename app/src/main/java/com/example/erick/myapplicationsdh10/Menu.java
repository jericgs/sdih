package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Erick on 21/06/2015.
 */
public class Menu extends ActionBarActivity implements View.OnClickListener{

    private ImageButton imagemBt1, imagemBt2, imagemBt3, imagemBt4;
    private AlertDialog alerta;
    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu);
        actionBarSetup();

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

            Intent telaAdicionarVaga = new Intent(this, AdicionarVaga.class);
            startActivity(telaAdicionarVaga);
        }

        if(v.getId() == R.id.imageButton3){

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
            Toast.makeText(this, "RESULTADO: "+data.getStringExtra("SCAN_RESULT")+" ("+data.getStringExtra("SCAN_FORMAT")+")", Toast.LENGTH_LONG).show();

            Intent telaRetornoQrcode = new Intent(this, RetornoQrCode.class);
            startActivity(telaRetornoQrcode);
        }
    }
}
