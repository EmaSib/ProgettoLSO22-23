package com.code.progettolso22_23.controls;

import com.code.progettolso22_23.entities.Bevanda;
import com.code.progettolso22_23.entities.Carrello;

import java.util.ArrayList;
import java.util.List;

public class CarrelloController {

    private static CarrelloController istance = null;
    private MainController mainController = MainController.getIstance();
    private DAOController daoController;

    private Carrello carrello = new Carrello(mainController.getUser(), new ArrayList<>(), 0);

    private CarrelloController() {
        daoController = DAOController.getIstance();
    }

    public static CarrelloController getIstance() {
        if(istance == null)
            istance = new CarrelloController();
        return istance;
    }

    public boolean aggiungiAlCarrello(Bevanda b) {
        if(this.carrello.getListaBevande().size() == 100 && !this.carrello.getListaBevande().contains(b))
            return false;
        if(this.carrello.getListaBevande().contains(b)) {
            int index = this.carrello.getListaBevande().indexOf(b);
            this.carrello.getQuantita()[index]++;
            this.carrello.setTotale(this.carrello.getTotale()+b.getCosto());
        }
        else {
            this.carrello.getListaBevande().add(b);
            int index = this.carrello.getListaBevande().indexOf(b);
            this.carrello.getQuantita()[index]++;
            this.carrello.setTotale(this.carrello.getTotale()+b.getCosto());
        }
        return true;
    }
}
