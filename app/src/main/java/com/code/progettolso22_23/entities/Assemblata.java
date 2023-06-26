package com.code.progettolso22_23.entities;

import java.util.List;

public class Assemblata extends Bevanda{
    private List<String> Ingredienti;
    private String Descrizione;

    public Assemblata() {

    }

    public Assemblata(List<String> ingredienti, String descrizione) {
        Ingredienti = ingredienti;
        Descrizione = descrizione;
    }

    public List<String> getIngredienti() {
        return Ingredienti;
    }

    public void setIngredienti(List<String> ingredienti) {
        Ingredienti = ingredienti;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }
}
