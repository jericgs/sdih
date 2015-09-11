package com.example.erick.myapplicationsdh10;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Erick on 21/08/2015.
 */
public class WebViewDesenvolvedor extends ActionBarActivity{

    private WebView webView;
    private String url;
    private String chave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_webview);
        actionBarSetup();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        chave = this.getIntent().getStringExtra("Chave");

        webView = (WebView)findViewById(R.id.webViewDesenvolvedores);
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);


        if(chave.equalsIgnoreCase("Erico")){
            webView.loadUrl("http://lattes.cnpq.br/1048245272218464");
        }

        if(chave.equalsIgnoreCase("Daniel")){
            webView.loadUrl("http://lattes.cnpq.br/4309300012137401");
        }

        if(chave.equalsIgnoreCase("Felipe")){
            webView.loadUrl("http://lattes.cnpq.br/0820382332492019");
        }

        if(chave.equalsIgnoreCase("Geo")){
            webView.loadUrl("http://lattes.cnpq.br/0541982237316456");
        }

        if(chave.equalsIgnoreCase("Raissa")){
            webView.loadUrl("http://lattes.cnpq.br/4930768063248400");
        }

        if(chave.equalsIgnoreCase("Ramon")){
            webView.loadUrl("http://lattes.cnpq.br/8924441313919882");
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
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
}
