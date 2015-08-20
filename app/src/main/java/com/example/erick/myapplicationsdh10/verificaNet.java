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




}
