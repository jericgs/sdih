package com.example.erick.myapplicationsdh10;

/**
 * Created by Erick on 03/07/2015.
 */
public class FormatoEndereco {

    private String complemento;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return  "Complemento: " +  getComplemento() + "\n" +
                "Logradouro: " + getLogradouro() + "\n" +
                "Bairro: " + getBairro() + "\n" +
                "Cidade: " + getCidade() + "\n" +
                "Estado: " + getEstado() + "\n" +
                "Pais: " + getPais();
    }
}