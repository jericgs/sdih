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

import java.util.List;

public class Mapa extends ActionBarActivity  implements View.OnClickListener, OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    public static GoogleMap map;
    public static double latitude;
    public static double longitude;
    private LocationManager locationManager;
    private AlertDialog alerta;
    private List<String> coordenadasChave;


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

        if(coordenadasChave.get(0).toString().equalsIgnoreCase("menu")){

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


        addMarker(new LatLng(-5.190658, -37.346039),"Conselho Tutelar 33ª - 34ª Zona","Av. Rio Branco, 1590 - Bom Jardim");
        addMarker(new LatLng(-5.192019, -37.346993),"Fórum Dr. Silveira Martins","Av. Rio Branco, 1902 - Centro");
        addMarker(new LatLng(-5.192590, -37.347666),"Praça da Convivência","Av. Rio Branco, S/N  - Centro");
        addMarker(new LatLng(-5.188135, -37.342975),"Ortodontic Center","Av. Rio Branco, 1235 - Bom Jardim");
        addMarker(new LatLng(-5.194257, -37.348302),"Edifício Romildo Nunes","Av. Rio Branco, 2157 - Bom Jardim");
        addMarker(new LatLng(-5.194774, -37.347543),"Secretaría M. da Adm. e Gestão de Pessoas","Rua Rui Barbosa, S/N - Alto da Conceição");
        addMarker(new LatLng(-5.190269, -37.343664),"Lisboa Center","Av. Alberto Maranhão, 1927 - Centro");
        addMarker(new LatLng(-5.191155, -37.344207),"HSBC","Av. Alberto Maranhão, S/N - Centro");
        addMarker(new LatLng(-5.192309, -37.344910),"Receita Federal","Av. Alberto Maranhão, S/N - Centro");
        addMarker(new LatLng(-5.193743, -37.345730),"Shopping Oásis Center","Av. Alberto Maranhão, S/N - Centro");
        addMarker(new LatLng(-5.194495, -37.346175),"Banco do Brasil","Av. Alberto Maranhão, S/N - Centro");
        addMarker(new LatLng(-5.190496, -37.342334),"São Luiz Plaza Hotel","Rua Des. Dionísio Figueira, S/N - Alto da Conceição");
        addMarker(new LatLng(-5.191160, -37.341781),"Mercado Público Central","Rua Des. Dionísio Figueira, S/N - Centro");
        addMarker(new LatLng(-5.191305, -37.342218),"Park Center","Rua Des. Dionísio Figueira, S/N - Centro");
        addMarker(new LatLng(-5.191496, -37.341550),"Mercado Público Central","Rua Francisco Peregrino, S/N - Centro");
        addMarker(new LatLng(-5.191597, -37.340642),"Bradesco","Av. Dix-Sept Rosado, S/N - Centro");
        addMarker(new LatLng(-5.193827, -37.340460),"Clinica Doctor","Rua Jerônimo Rosado, 36 - Centro");
        addMarker(new LatLng(-5.192751, -37.344014),"Praça Bento Praxedes","Rua Des. Dionísio Figueira, S/N - Centro");
        addMarker(new LatLng(-5.192770, -37.344019),"Praça Bento Praxedes","Rua Des. Dionísio Figueira, S/N - Centro");
        addMarker(new LatLng(-5.192794, -37.344030),"Praça Bento Praxedes","Rua Des. Dionísio Figueira, S/N - Centro");
        addMarker(new LatLng(-5.192797, -37.344030),"Praça Bento Praxedes","Rua Des. Dionísio Figueira, S/N - Centro");
        addMarker(new LatLng(-5.194116, -37.344875),"","Rua Idalino de Oliveira, S/N - Centro");
        addMarker(new LatLng(-5.192960, -37.343851),"Praça Bento Praxedes","Rua José de Alençar, S/N - Centro");
        addMarker(new LatLng(-5.193734, -37.344205),"Promot. Just. da Comarca de Mossoró","Rua José de Alençar, S/N - Alto da Conceição");
        addMarker(new LatLng(-5.195031, -37.344434),"Caixa","Rua Cel. Gurgel, S/N - Alto da Conceição");
        addMarker(new LatLng(-5.190934, -37.343754),"Colégio Sagrado Coração de Maria","Av. Augusto Severo, S/N - Centro");
        addMarker(new LatLng(-5.191737, -37.342900),"Itaú","Av. Augusto Severo, S/N - Centro");
        addMarker(new LatLng(-5.193147, -37.344262),"Centro Odontológico Dr. José Azevedo","Rua Alfredo Fernandes, S/N - Centro");
        addMarker(new LatLng(-5.193182, -37.344181),"Centro Odontológico Dr. José Azevedo","Rua Alfredo Fernandes, S/N - Centro");
        addMarker(new LatLng(-5.195818, -37.346534),"Anibaltec","Rua Lopes Trovão, 95 -  Alto da Conceição");
        addMarker(new LatLng(-5.192264, -37.341044),"Banco do Brasil","Tv. Frei Antônio da Conceição, 95 -  Centro");
        addMarker(new LatLng(-5.183124, -37.349988),"Show Auto Mall","Rua Dr. João Marcelino, S/N - Abolição");
        addMarker(new LatLng(-5.183229, -37.349898),"Show Auto Mall","Rua Dr. João Marcelino, S/N - Abolição");
        addMarker(new LatLng(-5.183328, -37.349803),"Show Auto Mall","Rua Dr. João Marcelino, S/N - Abolição");
        addMarker(new LatLng(-5.190103, -37.346230),"Hidro Glass","Rua Juvenal Lamartine, S/N -  Centro");
        addMarker(new LatLng(-5.188249, -37.345796),"Clínica Marcos Pedrosa","Rua Melo Franco, 283 -  Doze Anos");
        addMarker(new LatLng(-5.188118, -37.347360),"Clínica Alexandre Diógenes","Rua Pedro Velho, 322 -  Abolição");
        addMarker(new LatLng(-5.183059, -37.350164),"Show Auto Mall","Av. Diocesano, S/N - Abolição");
        addMarker(new LatLng(-5.183079, -37.350172),"Show Auto Mall","Av. Diocesano, S/N - Abolição");
        addMarker(new LatLng(-5.183384, -37.350443),"Show Auto Mall","Av. Diocesano, S/N - Abolição");
        addMarker(new LatLng(-5.183409, -37.350462),"Show Auto Mall","Av. Diocesano, S/N - Abolição");
        addMarker(new LatLng(-5.176543, -37.356290),"Nossa Clínica","Rua Dr. João Marcelino, S/N - Abolição");
        addMarker(new LatLng(-5.176535, -37.356298),"Nossa Clínica","Rua Dr. João Marcelino, S/N - Abolição");
        addMarker(new LatLng(-5.182216, -37.360059),"Bambino's","Rua João da Escócia, S/N -  Nova Betânia");
        addMarker(new LatLng(-5.182213, -37.360075),"Bambino's","Rua João da Escócia, S/N -  Nova Betânia");
        addMarker(new LatLng(-5.182152, -37.360187),"Bambino's","Rua João da Escócia, S/N -  Nova Betânia");
        addMarker(new LatLng(-5.182143, -37.360195),"Bambino's","Rua João da Escócia, S/N -  Nova Betânia");
        addMarker(new LatLng(-5.179439, -37.365085),"Pittsburg","Rua João da Escócia, S/N -  Nova Betânia");
        addMarker(new LatLng(-5.179735, -37.364632),"Paivagomes","Rua João da Escócia, 1293 - Nova Betânia");
        addMarker(new LatLng(-5.181413, -37.361769),"San Remo Baby","Rua João da Escócia, S/N - Nova Betânia");
        addMarker(new LatLng(-5.173995, -37.353763),"TRE/RN - Fórum Eleitoral Celina Guimarães Viana (33ZE e 34ZE)","Rua Maria Salém Duarte, 1181 - Abolição");
        addMarker(new LatLng(-5.174002, -37.353777),"TRE/RN - Fórum Eleitoral Celina Guimarães Viana (33ZE e 34ZE)","Rua Maria Salém Duarte, 1181 - Abolição");
        addMarker(new LatLng(-5.181537, -37.361595),"Clínica Agnus Dei","Rua Antônio Vieira de Sá, S/N - Nova Betânia");
        addMarker(new LatLng(-5.188736, -37.366220),"","Rua Guilerme Ricardo de Lima, 508 - Nova Betânia");
        addMarker(new LatLng(-5.184900, -37.340575),"Esplendor","Rua Campos Sáles, S/N - Santo Antônio");
        addMarker(new LatLng(-5.188713, -37.337028),"Feijão e CIA","Rua Marechal Floreano, S/N - Paredões");
        addMarker(new LatLng(-5.183635, -37.341263),"Vuco Vuco","Av. Rio Branco, S/N - Bom Jardim");
        addMarker(new LatLng(-5.202110, -37.350628),"Igreja do Alto da Conceição","Rua Padre João Urbano, S/N - Alto da Conceição");
        addMarker(new LatLng(-5.201967, -37.350174),"Praça da Igreja do Alto da Conceição","Rua Tiradentes, S/N - Alto da Conceição");
        addMarker(new LatLng(-5.200310, -37.339069),"Hyunday","Rua Alfredo Fernandes, 863 - Iha de Santa Luzia");
        addMarker(new LatLng(-5.200347, -37.339046),"Hyunday","Rua Alfredo Fernandes, 863 - Iha de Santa Luzia");
        addMarker(new LatLng(-5.201249, -37.338413),"Chevrolet Terrasal","Rua Alfredo Fernandes, S/N - Iha de Santa Luzia");
        addMarker(new LatLng(-5.200908, -37.339021),"Correios","Rua Alfredo Fernandes, S/N - Iha de Santa Luzia");
        addMarker(new LatLng(-5.199744, -37.339644),"Honda","Rua Alfredo Fernandes, S/N - Iha de Santa Luzia");
        addMarker(new LatLng(-5.209604, -37.333717),"Hotel Sabino Palace","Rua Alfredo Fernandes, S/N - Iha de Santa Luzia");
        addMarker(new LatLng(-5.209638, -37.333698),"Hotel Sabino Palace","Rua Alfredo Fernandes, S/N - Iha de Santa Luzia");

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
        }

        if(v.getId() == R.id.buttonNao){
            alerta.dismiss();
        }
    }
}





