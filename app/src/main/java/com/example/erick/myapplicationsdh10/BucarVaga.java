package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


/**
 * Created by Erick on 03/07/2015.
 */
public class BucarVaga extends ActionBarActivity implements View.OnClickListener{

    private ImageButton btBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_buscar_vaga);
        actionBarSetup();

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
    public void onClick(View v) {

        if(v.getId() == R.id.botaoBuscar){

        }

    }

    /*public ArrayList<FormatoEndereco> buscarContatoPorVoz() throws InterruptedException {


        ArrayList<ContatoBuscadoFormato> telefone = new ArrayList<>();
        String palavra = null, palavraComtoUpperCase = null;
        String nome = null, nomeComReplace = null;
        int i = 0;

        for (i = 0; i < opPalavras.size(); i++) {
            for (int j = 0; j < ListaContatos.size(); j++) {

                palavra = opPalavras.get(i);
                palavraComtoUpperCase = palavra.toUpperCase();
                nome = ListaContatos.get(j).getNome();
                nomeComReplace = nome.replace(" ","");
                nomeComReplace = nomeComReplace.toUpperCase();

                if(nomeComReplace.equals(palavraComtoUpperCase)){
                    String numeroComCaractere = ListaContatos.get(j).toString();
                    String numeroR1 = numeroComCaractere.replace("[", "");
                    String numero = numeroR1.replace("]", "");



                    ContatoBuscadoFormato contatoFormato = new ContatoBuscadoFormato(nome, numero);
                    telefone.add(contatoFormato);

                }
            }
        }

        if(telefone.isEmpty()){
            ContatoBuscadoFormato contatoFormato = new ContatoBuscadoFormato("Contato Inexistente", "");
            telefone.add(contatoFormato);
        }

        return telefone;
    }*/
}
