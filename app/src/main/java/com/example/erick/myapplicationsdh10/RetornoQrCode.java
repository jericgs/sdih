package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.EditText;

/**
 * Created by Erick on 19/08/2015.
 */
public class RetornoQrCode extends ActionBarActivity {

    private EditText etNome, etDataNascimento, etSexo, etCNH, etCPF, etComplemento, etBairro, etCidade, etPais, etEstado, etTelCel;
    private EditText etModelo, etMarca, etTipo, etCor, etRenavam, etPlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_retorno_qrcode);
        actionBarSetup();

        etNome = (EditText)findViewById(R.id.editTextNome);
        etDataNascimento = (EditText)findViewById(R.id.editTextDataNascimento);
        etSexo = (EditText)findViewById(R.id.editTextSexo);
        etCNH = (EditText)findViewById(R.id.editTextCnh);
        etCPF = (EditText)findViewById(R.id.editTextCpf);
        etComplemento = (EditText)findViewById(R.id.editTextComplemento);
        etBairro = (EditText)findViewById(R.id.editTextBairro);
        etCidade = (EditText)findViewById(R.id.editTextCidade);
        etPais = (EditText)findViewById(R.id.editTextPais);
        etEstado = (EditText)findViewById(R.id.editTextEstado);
        etTelCel = (EditText)findViewById(R.id.editTextTelCel);

        etModelo = (EditText)findViewById(R.id.editTextModelo);
        etMarca = (EditText)findViewById(R.id.editTextMarca);
        etTipo = (EditText)findViewById(R.id.editTextTipo);
        etCor = (EditText)findViewById(R.id.editTextCor);
        etRenavam = (EditText)findViewById(R.id.editTextRenavam);
        etPlaca = (EditText)findViewById(R.id.editTextPlaca);
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
