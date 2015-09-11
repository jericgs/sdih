package com.example.erick.myapplicationsdh10;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends ActionBarActivity  implements View.OnClickListener, OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    public static GoogleMap map;
    public static double latitude;
    public static double longitude;
    private LocationManager locationManager;
    private AlertDialog alerta;
    private List<String> coordenadasChave;
    private String vagasRetornadas;
    private List<Endereco> enderecosLista;


    public static  void addMarker(LatLng latLng, String string1, String string2){
        map.addMarker(new MarkerOptions().title(string1).snippet(string2).position(latLng)).
                setIcon((BitmapDescriptorFactory.fromResource(R.drawable.ic_def)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        coordenadasChave =  this.getIntent().getStringArrayListExtra("informacoes");

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        map = mapFragment.getMap();
        mapFragment.getMapAsync(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled (true);
        locationManager = (LocationManager) this.getApplicationContext().getSystemService(LOCATION_SERVICE);
    }


    @Override
    public void onMapReady(GoogleMap map) {

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                habilitaGPS();
                return false;
            }
        });

        //Log.i("informacao", " "+coordenadasChave.get(0).toString().equalsIgnoreCase("vaga"));

        if(coordenadasChave.get(0).toString().equalsIgnoreCase("vaga")) {
            map.getUiSettings().setZoomControlsEnabled(true);
            map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().
                    target(new LatLng(Double.parseDouble(coordenadasChave.get(1).toString()), Double.parseDouble(coordenadasChave.get(2).toString()))).zoom(20).bearing(0).tilt(90).build()));
        }

        if(coordenadasChave.get(0).toString().equalsIgnoreCase("menu") || coordenadasChave.get(0).toString().equalsIgnoreCase("vagaadd")){

            map.getUiSettings().setZoomControlsEnabled(true);
            map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().
                    target(new LatLng(-5.1841285, -37.3477805)).zoom(13).bearing(0).tilt(90).build()));

            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                    int layout = R.layout.tela_alerta_add_vaga;
                    int idButtonSim = R.id.buttonSim;
                    int idButtonNao = R.id.buttonNao;
                    alertaDialogo(layout, idButtonSim, idButtonNao);

                    latitude = latLng.latitude;
                    longitude = latLng.longitude;
                }
            });
        }

        try {

            vagasRetornadas = ConexaoHttpClient.execultaHttpGet(ConexaoHttpClient.enviarSolicitacaoVagasMapa);
            String respostaVagasBuscadas = vagasRetornadas.substring(3);

            enderecosLista = new ArrayList<>();

            if(respostaVagasBuscadas.contains("~")){

                String enderecos [];
                enderecos = respostaVagasBuscadas.split("#");

                for(int i = 0; i < enderecos.length; i++){

                    Log.i("Retorno" + i + " : ", enderecos[i]);

                    if(enderecos[i].contains("~")){
                        String endereco [] = enderecos[i].split("~");

                        for(int j = 0; j < endereco.length; j++) {

                            Log.i("Retorno" + j + " : ", endereco[j]);
                        }

                        Endereco endereco1 = new Endereco(endereco[0], endereco[1], endereco[2], endereco[3], endereco[4], endereco[5], endereco[6], endereco[7]);
                        enderecosLista.add(endereco1);
                    }

                }


            }else{
                ToastManager.show(this, "Não existe vagas nessa região.", ToastManager.INFORMACOES);
            }

            for(int i = 0; i < enderecosLista.size(); i++){

                addMarker(new LatLng(Double.parseDouble(enderecosLista.get(i).getLongitude().toString()), Double.parseDouble(enderecosLista.get(i).getLatitude().toString())),
                        enderecosLista.get(i).getComplemento().toString(), enderecosLista.get(i).getLogradouro().toString() + ", " + enderecosLista.get(i).getBairro().toString());

            }

        }catch (Exception e){
            e.printStackTrace();
            ToastManager.show(this, "Erro! Verifique sua conexão.", ToastManager.INFORMACOES);
            finish();
        }

    }

    public void alertaDialogo(int layout, int idButtonSim, int idButtonNao){
        AlertDialog.Builder builder = new AlertDialog.Builder(Mapa.this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu_telamapa, menu);
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

        if (id == R.id.opcao_satelite) {
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return true;
        }

        if (id == R.id.opcao_terreno) {
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void habilitaGPS(){
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.i("Dentro do", "IF");
            ToastManager.show(this, "O SDIH precisa acessar seu local. Ative o acesso à localização.", ToastManager.INFORMACOES);
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonSim){
            Intent telaAdicionarVaga = new Intent(Mapa.this, AdicionarVaga.class);
            startActivity(telaAdicionarVaga);
            alerta.dismiss();
            finish();
        }

        if(v.getId() == R.id.buttonNao){
            alerta.dismiss();
        }
    }
}





