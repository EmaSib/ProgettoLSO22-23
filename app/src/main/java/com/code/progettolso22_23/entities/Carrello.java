package com.code.progettolso22_23.entities;

import java.util.List;

public class Carrello {

    private String Username;
    private List<Bevanda> ListaBevande;
    private float Totale;

    public Carrello(String username, List<Bevanda> listaBevande, float totale) {
        Username = username;
        ListaBevande = listaBevande;
        Totale = totale;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public List<Bevanda> getListaBevande() {
        return ListaBevande;
    }

    public void setListaBevande(List<Bevanda> listaBevande) {
        ListaBevande = listaBevande;
    }

    public float getTotale() {
        return Totale;
    }

    public void setTotale(float totale) {
        Totale = totale;
    }

    public void aggiungiBevanda(Bevanda b) {
        this.getListaBevande().add(b);
    }

    public void rimuoviBevanda(Bevanda b) {
        this.getListaBevande().remove(b);
    }

}
