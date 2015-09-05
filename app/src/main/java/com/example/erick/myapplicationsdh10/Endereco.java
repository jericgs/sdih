package com.example.erick.myapplicationsdh10;

/**
 * Created by Erick on 03/09/2015.
 */
public class Endereco {

    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    private String longitude;
    private String latitude;

    public Endereco(String logradouro, String complemento, String bairro, String cidade, String estado, String pais, String longitude, String latitude) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "\nLogradouro: " + this.logradouro + "\n"+
               "Complemento: " + this.complemento + "\n"+
               "Bairro: " + this.bairro + "\n"+
               "Cidade: " + this.cidade + "\n"+
               "Estado: " + this.estado + "\n"+
               "Pais: " + this.pais + "\n";
    }
}
