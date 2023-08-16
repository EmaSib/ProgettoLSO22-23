package com.code.progettolso22_23.entities;

import android.graphics.Bitmap;

public class Bevanda {
    private String Nome=null;
    private String Tipo=null;
    private Bitmap Foto=null;
    private float Costo=-1;
    private int NumeroVendite=-1;

    public Bevanda() {
    }

    public Bevanda(String nome, String tipo, Bitmap foto, float costo, int numerovendite) {
        Nome = nome;
        Tipo = tipo;
        Foto = foto;
        Costo = costo;
        NumeroVendite = numerovendite;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Bitmap getFoto() {
        return Foto;
    }

    public void setFoto(Bitmap foto) {
        Foto = foto;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float costo) {
        Costo = costo;
    }

    public int getNumeroVendite() {
        return NumeroVendite;
    }

    public void setNumeroVendite(int numeroVendite) {
        NumeroVendite = numeroVendite;
    }
}
