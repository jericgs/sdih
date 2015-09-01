package com.example.erick.myapplicationsdh10;
import com.google.android.gms.maps.model.LatLng;
import android.annotation.TargetApi;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
;import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick on 03/07/2015.
 */
public class AdicionarVaga extends ActionBarActivity implements View.OnClickListener{
    private EditText editTextComplemento;
    private EditText editTextLogradouro;
    private EditText editTextBairro;
    private EditText editTextCidade ;
    private EditText editTextEstado;
    private EditText editTextPais;
    private Button botaoCadastarVaga;
    private EditText editTextLatitude;
    private EditText editTextLongitude;

    private List<String> geoLocalizacao = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_add_vaga);
        actionBarSetup();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled (true);
        InformacoesDaVaga();

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
            Mapa.addMarker(new LatLng(Mapa.latitude, Mapa.longitude), editTextComplemento.getText().toString(),
                    editTextLogradouro.getText().toString() + ", " + editTextBairro.getText().toString());
            finish();
        }

    }
}
