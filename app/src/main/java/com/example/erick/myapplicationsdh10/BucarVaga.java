package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Erick on 03/07/2015.
 */
public class BucarVaga extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listaEnderecos;
    private EditText nomeBuscado;
    private ImageButton btBuscar;
    private String respostaRetornadaNomeBuscado;
    private List<Endereco> enderecosLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_buscar_vaga);
        actionBarSetup();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listaEnderecos = (ListView)findViewById(R.id.listView);
        listaEnderecos.setOnItemClickListener(this);
        nomeBuscado = (EditText)findViewById(R.id.nomeBuscado);
        btBuscar = (ImageButton)findViewById(R.id.botaoBuscar);
        btBuscar.setOnClickListener(this);
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

        if(v.getId() == R.id.botaoBuscar){

             String nBuscado = nomeBuscado.getText().toString().replace(" ", "");

            ArrayList<NameValuePair> parametrosPostEnviarNomeBuscar = new ArrayList<>();
            parametrosPostEnviarNomeBuscar.add(new BasicNameValuePair("logradouro", nBuscado));

            try {

                respostaRetornadaNomeBuscado = ConexaoHttpClient.execultaHttpPost(ConexaoHttpClient.enviarNomeBuscar, parametrosPostEnviarNomeBuscar);
                String respostaNomeBuscado = respostaRetornadaNomeBuscado.substring(3);

                enderecosLista = new ArrayList<>();

                if(respostaNomeBuscado.contains("~")){

                    String enderecos [];
                    enderecos = respostaNomeBuscado.split("#");

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

                    listaEnderecos.setAdapter(new ArrayAdapter<Endereco>(this, R.layout.fundo_list_view, enderecosLista));

                }else{
                    ToastManager.show(this, "Não existe vagas nessa rua.", ToastManager.INFORMACOES);
                }

            }catch (Exception e){
                e.printStackTrace();
                ToastManager.show(this, "Erro na busca. verifique sua conexão.", ToastManager.ERROS);
            }

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i("Informação", "P: " + position + " I: " + id);

        ArrayList<String> coodenadas = new ArrayList<>();
        coodenadas.add("vaga");
        coodenadas.add(enderecosLista.get(position).getLongitude());
        coodenadas.add(enderecosLista.get(position).getLatitude());
        Intent tela_Mapa = new Intent(this, Mapa.class);
        tela_Mapa.putStringArrayListExtra("informacoes", coodenadas);
        startActivity(tela_Mapa);

    }
}
