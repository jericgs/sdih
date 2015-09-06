package com.example.erick.myapplicationsdh10;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Erick on 20/08/2015.
 */

public class verificaNet {

    public static boolean verificaConexao(Context context) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public static boolean verificaInternet(Context context) {

        try{
            java.net.URL mandarMail = new java.net.URL("http://www.guj.com.br");
            java.net.URLConnection conn = mandarMail.openConnection();

            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) conn;
            httpConn.connect();
            int x = httpConn.getResponseCode();
            if(x == 200){
                System.out.println("Conectado");
                return true;
            }
        }catch(java.net.MalformedURLException urlmal){

        }catch(java.io.IOException ioexcp){

        }

        return false;
    }

}
