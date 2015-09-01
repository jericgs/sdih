package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geofrangite on 13/08/2015.
 */
public class AdicionarVagaPeloGPS extends ActionBarActivity implements View.OnClickListener, LocationListener{
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

    public static double latitude;
    public static double longitude;
    public Context context;
    private LocationManager locationManager;
    private Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_add_vaga);
        actionBarSetup();

        context = getApplicationContext();
        locationManager = (LocationManager) this.getApplicationContext().getSystemService(LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.i("Dentro do","IF");
            Toast.makeText(this, "O SDIH precisa acessar seu local. Ative o acesso à localização.", Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
            finish();
        }else{
            Log.i("Dentro do","ELSE");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1, this);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            while(location==null){
                Log.i("Dentro do","WHILE");
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            Log.i("Latitude",Double.toString(location.getLatitude()));
            Log.i("Longitude",Double.toString(location.getLongitude()));
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            InformacoesDaVaga();
        }
        editTextComplemento = (EditText) findViewById(R.id.editTextComplemento);
        editTextLogradouro = (EditText) findViewById(R.id.editTextLogradouro);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextPais = (EditText) findViewById(R.id.editTextPais);
        editTextLongitude = (EditText)findViewById(R.id.editTextLongitude);
        editTextLongitude.setText((Double.toString(longitude)));
        editTextLatitude = (EditText)findViewById(R.id.editTextLatitude);
        editTextLatitude.setText((Double.toString(latitude)));
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
        Geocoder geocoder =  new Geocoder(AdicionarVagaPeloGPS.this);
        try {
            addressList = geocoder.getFromLocation(latitude,longitude,1);
            if(addressList!=null && addressList.size()>0){
                if(addressList.get(0).getThoroughfare()!=null)
                    geoLocalizacao.add(addressList.get(0).getThoroughfare());
                if(addressList.get(0).getSubAdminArea()!=null && addressList.get(0).getAdminArea()!=null && addressList.get(0).getCountryCode()!=null){
                    geoLocalizacao.add(addressList.get(0).getSubAdminArea());
                    geoLocalizacao.add(addressList.get(0).getAdminArea());
                    geoLocalizacao.add(addressList.get(0).getCountryCode());
                } else{
                    Toast.makeText(this,"Não é possível adicionar uma vaga aqui",Toast.LENGTH_LONG).show();
                    finish();
                }
            } else{
                Toast.makeText(this,"Não é possível adicionar uma vaga aqui",Toast.LENGTH_LONG).show();
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
            Mapa.addMarker(new LatLng(latitude,longitude), editTextComplemento.getText().toString(),
                    editTextLogradouro.getText().toString() + ", " + editTextBairro.getText().toString());
            finish();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

}