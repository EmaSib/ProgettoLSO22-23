package com.code.progettolso22_23.controls;

import android.util.Log;

import com.code.progettolso22_23.dao.SocketInitializer;
import com.code.progettolso22_23.dao.dao_implementations.BevandaDAOImpl;
import com.code.progettolso22_23.dao.dao_implementations.UtenteDAOImpl;
import com.code.progettolso22_23.entities.Bevanda;
import java.util.List;

public class DAOController {

    private static DAOController istance = null;
    private SocketInitializer connection = SocketInitializer.getIstance();
    private UtenteDAOImpl utenteDAO= new UtenteDAOImpl();
    private BevandaDAOImpl bevandaDAO = new BevandaDAOImpl();

    private DAOController() {

    }

    public static DAOController getIstance() {
        if(istance == null)
            istance = new DAOController();
        return istance;
    }

    public int checkLogin(String username, String password) {

        return utenteDAO.checkLogin(username,password);

    }

    public boolean effettuaSignUp(String username, String password, String nome, String cognome) {

        return utenteDAO.effettuaSignUp(username, password, nome, cognome);

    }

    public List<Bevanda> ottieniTutteLeBevande() {

        return bevandaDAO.getAllBevande();

    }

    public float getSaldoByUsername(String username) {

        return utenteDAO.getSaldoByUsername(username);

    }

    public boolean updateSaldoDiUtente(String username, float saldo) {

        return utenteDAO.updateSaldoDiUtente(username, saldo);
    }

    public void closeConnection() {
        try {
            connection.request("logout]");
        } catch (Exception e) {
            Log.e("DAOController -> closeConnection -> ", "Errore: " + e.getMessage() );
        }
        connection.closeSocket();
    }

    public boolean updateVenditeBevandeAcquistate(String nome, int numero) {

        return bevandaDAO.updateVenditeBevanda(nome, numero);

    }

}
