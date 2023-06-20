package com.code.progettolso22_23.controls;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private static MainController istance = null;
    List<String> ingredienti = new ArrayList<String>();

    private MainController() {

    }

    public static MainController getIstance() {
        if(istance == null)
            istance = new MainController();
        return istance;
    }

    public void aggiungiIngrediente(String string) {
        ingredienti.add(string);
    }

    public List<String> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<String> ingredienti) {
        this.ingredienti = ingredienti;
    }
}
