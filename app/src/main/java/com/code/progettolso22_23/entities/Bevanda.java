package com.code.progettolso22_23.entities;

import android.graphics.Bitmap;

public class Bevanda {
    private String Nome;
    private String Tipo;
    private Bitmap Foto;
    private float Costo;

    public Bevanda() {
    }

    public Bevanda(String nome, String tipo, Bitmap foto, float costo) {
        Nome = nome;
        Tipo = tipo;
        Foto = foto;
        Costo = costo;
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
}
