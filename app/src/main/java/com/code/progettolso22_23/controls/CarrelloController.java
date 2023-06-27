package com.code.progettolso22_23.controls;

import com.code.progettolso22_23.entities.Bevanda;
import com.code.progettolso22_23.entities.Carrello;
import com.code.progettolso22_23.entities.ElementoCarrello;

import java.util.ArrayList;
import java.util.List;

public class CarrelloController {

    private static CarrelloController istance = null;
    private MainController mainController;
    private DAOController daoController;

    private Carrello carrello;

    private CarrelloController() {
        daoController = DAOController.getIstance();
        mainController = MainController.getIstance();
        carrello = new Carrello(mainController.getUser(), new ArrayList<>(), 0);
    }

    public static CarrelloController getIstance() {
        if(istance == null)
            istance = new CarrelloController();
        return istance;
    }

    public void aggiungiAlCarrello(Bevanda b) {
        for(ElementoCarrello el : carrello.getLista()) {
            if (el.getBevanda().getNome().equals(b.getNome())) {
                el.setQuantita(el.getQuantita() + 1);
                return;
            }
        }
        ElementoCarrello tmp = new ElementoCarrello(b, 1);
        carrello.getLista().add(tmp);
    }
}
