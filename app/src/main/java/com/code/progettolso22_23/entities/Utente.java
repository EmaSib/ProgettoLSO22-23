package com.code.progettolso22_23.entities;

public class Utente {

    private String Username;
    private String Password;
    private String Nome;
    private String Cognome;
    private float Saldo;

    public Utente(String username, String password, String nome, String cognome, float saldo) {
        Username = username;
        Password = password;
        Nome = nome;
        Cognome = cognome;
        Saldo = saldo;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float saldo) {
        Saldo = saldo;
    }

    public void aggiungiASaldo(float x) {
        this.setSaldo(this.getSaldo()+x);
    }

    public void rimuoviDaSaldo(float x) {
        this.setSaldo(this.getSaldo()-x);
    }
}
