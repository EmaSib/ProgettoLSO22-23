package com.code.progettolso22_23.entities;

import java.util.List;

public class Carrello {

    private String Username;
    private List<ElementoCarrello> Lista;
    private float Totale;

    public Carrello(String username, List<ElementoCarrello> lista, float totale) {
        Username = username;
        Lista = lista;
        Totale = totale;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public List<ElementoCarrello> getLista() {
        return Lista;
    }

    public void setLista(List<ElementoCarrello> lista) {
        Lista = lista;
    }

    public float getTotale() {
        return Totale;
    }

    public void setTotale(float totale) {
        Totale = totale;
    }
}
