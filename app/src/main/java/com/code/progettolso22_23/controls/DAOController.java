package com.code.progettolso22_23.controls;

import com.code.progettolso22_23.entities.Assemblata;
import com.code.progettolso22_23.entities.Bevanda;

import java.util.ArrayList;
import java.util.List;

public class DAOController {

    private static DAOController istance = null;
    private DAOController() {

    }

    public static DAOController getIstance() {
        if(istance == null)
            istance = new DAOController();
        return istance;
    }

    public List<Bevanda> ottieniTutteLeBevande() {
        //metodo che effettua chiede al server di inviare
        //tutte le bevande presenti nel database
        //le successive righe sono di test
        List<Bevanda> result = new ArrayList<>();
        result.add(new Bevanda("martini", "Drink", null, 12, 7));
        result.add(new Bevanda("coca", "Generica", null, 3, 2));
        List<String> s = new ArrayList<>();
        s.add("lime");
        s.add("Menta");
        result.add(new Assemblata("cocco e lime", "Frullato", null, 5, 4, s, "buona"));
        return result;
    }

}
