package com.example.erick.myapplicationsdh10;
import android.annotation.TargetApi;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick on 03/07/2015.
 */
public class AdicionarVaga extends ActionBarActivity implements View.OnClickListener{

    private EditText editTextLogradouro;
    private EditText editTextComplemento;
    private EditText editTextBairro;
    private EditText editTextCidade ;
    private EditText editTextEstado;
    private EditText editTextPais;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private Button botaoCadastarVaga;
    private String respostaRetornada;

    private List<String> geoLocalizacao = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_add_vaga);
        actionBarSetup();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled (true);
        InformacoesDaVaga();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        editTextComplemento = (EditText) findViewById(R.id.editTextComplemento);
        editTextLogradouro = (EditText) findViewById(R.id.editTextLogradouro);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextPais = (EditText) findViewById(R.id.editTextPais);
        editTextLongitude = (EditText)findViewById(R.id.editTextLongitude);
        editTextLongitude.setText((Double.toString(Mapa.longitude)));
        editTextLatitude = (EditText)findViewById(R.id.editTextLatitude);
        editTextLatitude.setText((Double.toString(Mapa.latitude)));
        botaoCadastarVaga = (Button) findViewById(R.id.buttonCadastro);
        botaoCadastarVaga.setOnClickListener(this);

        if(geoLocalizacao.size()==4){
            editTextLogradouro.setText(geoLocalizacao.get(0).toString());
            editTextCidade.setText(geoLocalizacao.get(1).toString());
            editTextEstado.setText(geoLocalizacao.get(2).toString());
            editTextPais.setText(geoLocalizacao.get(3).toString());
        }
        else if(geoLocalizacao.size()==3){
            editTextCidade.setText(geoLocalizacao.get(0).toString());
            editTextEstado.setText(geoLocalizacao.get(1).toString());
            editTextPais.setText(geoLocalizacao.get(2).toString());
        }
    }
    public void InformacoesDaVaga(){

        List<Address> addressList;
        Geocoder geocoder =  new Geocoder(this);
        Log.i("Latitude" + Mapa.latitude,"longitude" + Mapa.longitude);

        try {
            addressList = geocoder.getFromLocation(Mapa.latitude,Mapa.longitude,1);
            if(addressList!=null && addressList.size()>0){
                if(addressList.get(0).getThoroughfare()!=null)
                  geoLocalizacao.add(addressList.get(0).getThoroughfare());
                if(addressList.get(0).getSubAdminArea()!=null && addressList.get(0).getAdminArea()!=null && addressList.get(0).getCountryCode()!=null){
                    geoLocalizacao.add(addressList.get(0).getSubAdminArea());
                    geoLocalizacao.add(addressList.get(0).getAdminArea());
                    geoLocalizacao.add(addressList.get(0).getCountryCode());
                } else{
                    ToastManager.show(this, "Não é possível adicionar uma vaga aqui", ToastManager.INFORMACOES);
                    finish();
                }
            } else{
                ToastManager.show(this, "Não é possível adicionar uma vaga aqui", ToastManager.INFORMACOES);
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if(v.getId() == R.id.buttonCadastro){

            ArrayList<NameValuePair> parametroAddVaga = new ArrayList<>();
            parametroAddVaga.add(new BasicNameValuePair("logradouro", editTextLogradouro.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("complemento", editTextComplemento.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("bairro", editTextBairro.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("cidade", editTextCidade.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("estado", editTextEstado.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("pais", editTextPais.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("latitude", editTextLatitude.getText().toString()));
            parametroAddVaga.add(new BasicNameValuePair("longitude", editTextLongitude.getText().toString()));

            try{

                respostaRetornada = ConexaoHttpClient.execultaHttpPost(ConexaoHttpClient.enviarVaga, parametroAddVaga);

                if(respostaRetornada.toString().contains("1")){
                    ToastManager.show(this, "Vaga cadastrada com sucesso", ToastManager.CONFIRMACOES);
                    ArrayList<String> chave = new ArrayList<>();
                    chave.add("vagaadd");
                    Intent tela_mapa = new Intent(this, Mapa.class);
                    tela_mapa.putStringArrayListExtra("informacoes", chave);
                    startActivity(tela_mapa);
                    finish();
                }

                if(respostaRetornada.toString().contains("0")){
                    ToastManager.show(this, "A vaga não foi cadastrada com sucesso", ToastManager.INFORMACOES);
                    ArrayList<String> chave = new ArrayList<>();
                    chave.add("vagaadd");
                    Intent tela_mapa = new Intent(this, Mapa.class);
                    tela_mapa.putStringArrayListExtra("informacoes", chave);
                    startActivity(tela_mapa);
                    finish();
                }

            }catch (Exception e){
                e.printStackTrace();
                ToastManager.show(this, "Erro no envio", ToastManager.ERROS);
            }
        }

    }
}
