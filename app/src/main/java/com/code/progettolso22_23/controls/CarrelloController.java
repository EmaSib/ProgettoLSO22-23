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

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public void aggiornaQuantitaBevandaCarrello(String nome, int quantita) {
        /*for (ElementoCarrello e : carrello.getLista()) {
            if(e.getBevanda().getNome().equals(nome)) {
                e.setQuantita(e.getQuantita() - quantita);
                if(e.getQuantita() <= 0)
                    carrello.getLista().remove(e);
            }
        }*/
        for (ElementoCarrello e : carrello.getLista()) {
            if (e.getBevanda().getNome().equals(nome)) {
                if (quantita == 0)
                    carrello.getLista().remove(e);
                else
                    e.setQuantita(quantita);

            }
        }
    }

    public String[] ottieniBevandeDaCarrello() {
        String[] result = new String[carrello.getLista().size()];
        int i=0;
        for(ElementoCarrello e : carrello.getLista()) {
            result[i] = e.getBevanda().getNome();
            i++;
        }
        return result;
    }

    public String[] ottieniCostoBevandaDaCarrello() {
        String[] result = new String[carrello.getLista().size()];
        int i=0;
        for(ElementoCarrello e : carrello.getLista()) {
            result[i] = String.valueOf(e.getBevanda().getCosto() * e.getQuantita());
            i++;
        }
        return result;
    }

    public String[] ottieniQuantitaDaCarrello() {
        String[] result = new String[carrello.getLista().size()];
        int i=0;
        for(ElementoCarrello e : carrello.getLista()) {
            result[i] = String.valueOf(e.getQuantita());
            i++;
        }
        return result;
    }

    public void svuotaCarrello() {
        carrello.getLista().removeAll(carrello.getLista());
        carrello = new Carrello(mainController.getUser(), new ArrayList<>(), 0);
    }

    public float ottieniPrezzoTotale() {
        float result = 0;
        float tmp = 0;
        for(ElementoCarrello e : carrello.getLista()) {
            tmp = e.getBevanda().getCosto() * e.getQuantita();
            result = result + tmp;
            tmp = 0;
        }
        return result;
    }

}
