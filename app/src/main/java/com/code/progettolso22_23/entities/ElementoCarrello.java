package com.code.progettolso22_23.entities;

public class ElementoCarrello {

    private Bevanda bevanda;
    private int quantita;

    public ElementoCarrello(Bevanda bevanda, int quantita) {
        this.bevanda = bevanda;
        this.quantita = quantita;
    }

    public Bevanda getBevanda() {
        return bevanda;
    }

    public void setBevanda(Bevanda bevanda) {
        this.bevanda = bevanda;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
