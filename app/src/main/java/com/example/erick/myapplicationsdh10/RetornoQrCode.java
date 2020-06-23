package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by Erick on 19/08/2015.
 */
public class RetornoQrCode extends ActionBarActivity {

    private EditText etNome, etDataNascimento, etSexo, etCNH, etCPF, etLogradouro, etNumero, etComplemento, etBairro, etCidade, etPais, etEstado, etTelCel;
    private String informacaoCondutor [], informacaoEndereco[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_retorno_qrcode);
        actionBarSetup();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        informacaoCondutor = this.getIntent().getStringArrayExtra("informacaoCondutor");
        informacaoEndereco = this.getIntent().getStringArrayExtra("informacaoEndereco");

        etNome = (EditText)findViewById(R.id.editTextNome);
        etNome.setText(informacaoCondutor[0]);
        etDataNascimento = (EditText)findViewById(R.id.editTextDataNascimento);
        etDataNascimento.setText(informacaoCondutor[1]);
        etSexo = (EditText)findViewById(R.id.editTextSexo);
        etSexo.setText(informacaoCondutor[2]);
        etCNH = (EditText)findViewById(R.id.editTextCnh);
        etCNH.setText(informacaoCondutor[3]);
        etCPF = (EditText)findViewById(R.id.editTextCpf);
        etCPF.setText(informacaoCondutor[4]);

        etLogradouro = (EditText)findViewById(R.id.editTextLogradouro);
        etLogradouro.setText(informacaoEndereco[0]);
        etNumero = (EditText)findViewById(R.id.editTextNumero);
        etNumero.setText(informacaoEndereco[6]);
        etComplemento = (EditText)findViewById(R.id.editTextComplemento);
        etComplemento.setText(informacaoEndereco[1]);
        etBairro = (EditText)findViewById(R.id.editTextBairro);
        etBairro.setText(informacaoEndereco[2]);
        etCidade = (EditText)findViewById(R.id.editTextCidade);
        etCidade.setText(informacaoEndereco[3]);
        etEstado = (EditText)findViewById(R.id.editTextEstado);
        etEstado.setText(informacaoEndereco[4]);
        etPais = (EditText)findViewById(R.id.editTextPais);
        etPais.setText(informacaoEndereco[5]);
        etTelCel = (EditText)findViewById(R.id.editTextTelCel);
        etTelCel.setText(informacaoCondutor[5]);

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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SpannableString s = new SpannableString("  SDIH");
            s.setSpan(new TypefaceSpan(this, "Aero.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle(s);

        }
    }
}
