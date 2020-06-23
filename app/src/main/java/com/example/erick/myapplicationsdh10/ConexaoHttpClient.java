package com.example.erick.myapplicationsdh10;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Erick on 15/01/2015.
 */

public class ConexaoHttpClient {
    public static final int HTTP_TIMEOUT = 30 * 1000;
    private static HttpClient httpclient;
    private static String IP = "di.uern.br:8080";

    public static String enviarIdQRCode = "http://" + IP + "/WebApplicationSDIH/buscaQRCODE.jsp?";
    public static String enviarNomeBuscar = "http://" + IP + "/WebApplicationSDIH/retornarVagas.jsp?";
    public static String enviarVaga = "http://" + IP + "/WebApplicationSDIH/adicionarVaga.jsp?";
    public static String enviarSolicitacaoLogin = "http://" + IP + "/WebApplicationSDIH/login.jsp?";
    public static String enviarSolicitacaoVagasMapa = "http://" + IP + "/WebApplicationSDIH/vagasMapa.jsp?";

    private static 	HttpClient geHttpClient(){//retorna a conexao, ou seja o cliente
        if (httpclient == null){
            httpclient = new DefaultHttpClient();
            final HttpParams httpParams = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, HTTP_TIMEOUT);
            ConnManagerParams.setTimeout(httpParams, HTTP_TIMEOUT);
        }
        return httpclient;
    }

    public static String execultaHttpPost(String url, ArrayList<NameValuePair> parametrosPost)throws Exception{
        BufferedReader bufferedReader = null;
        try{
            HttpClient Client = geHttpClient();
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parametrosPost);
            httpPost.setEntity(formEntity);
            HttpResponse httpResponse = Client.execute(httpPost);
            bufferedReader = new  BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer ("");
            String line  = "";
            String LS = System.getProperty("line.separator");
            while((line = bufferedReader.readLine())!= null){
                stringBuffer.append(line + LS);
            }
            bufferedReader.close();

            String resultado = stringBuffer.toString();
            return resultado;
        }finally{
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static String execultaHttpGet(String url)throws Exception{
        BufferedReader bufferedReader = null;
        try{
            HttpClient Client = geHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setURI(new URI(url));
            HttpResponse httpResponse = Client.execute(httpGet);
            bufferedReader = new  BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer ("");
            String line  = "";
            String LS = System.getProperty("line.separator");
            while((line = bufferedReader.readLine())!= null){
                stringBuffer.append(line + LS);
            }
            bufferedReader.close();

            String resultado = stringBuffer.toString();
            return resultado;
        }finally{
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
