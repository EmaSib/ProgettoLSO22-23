package com.code.progettolso22_23.controls;

import com.code.progettolso22_23.entities.Bevanda;

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
        return null;
    }

}
