package com.code.progettolso22_23.dao.dao_implementations;

import android.util.Log;

import com.code.progettolso22_23.dao.SocketInitializer;
import com.code.progettolso22_23.dao.dao_interfaces.UtenteDAO;

public class UtenteDAOImpl implements UtenteDAO {

    private SocketInitializer connection = SocketInitializer.getIstance();

    public UtenteDAOImpl() {

    }

    @Override
    public int checkLogin(String username, String password) {
        String result = null;
        try {
            if(connection.request("login\n"+username+"@"+password+']'))
                result = connection.receive();
            Log.d("UtenteDAOImpl -> checkLogin -> ", result);
            if(!result.equals("Fallimento"))
                return 1;
            else
                return 0;
        } catch (Exception e) {
            Log.e("UtenteDAOImpl -> checkLogin -> ", "Errore: " + e.getMessage() );
            return -1;
        }
    }

    @Override
    public boolean effettuaSignUp(String username, String password, String nome, String cognome) {
        String result = null;
        try {
            if(connection.request("signup\n"+username+"@"+password+"@"+nome+"@"+cognome+"]"))
                result = connection.receive();
            if(!result.equals("Failed"))
                return true;
            else
                return false;
        } catch (Exception e) {
            Log.e("UtenteDAOImpl -> effettuaSignUp -> ", "Errore: " + e.getMessage() );
            return false;
        }
    }

    @Override
    public float getSaldoByUsername(String username) {
        String query;
        float result = -1;
        try {
            if(connection.request("getsaldo\n"+username+"]")) {
                query = connection.receive();
                if(query.equals("Failed"))
                    return -1;
                result = Float.valueOf(query);
            }
            return result;
        } catch (Exception e) {
            Log.e("UtenteDAOImpl -> getSaldoByUsername -> ", "Errore: " + e.getMessage() );
            return -1;
        }
    }

    @Override
    public boolean updateSaldoDiUtente(String username, float saldo) {
        String result = null;
        try {
            if(connection.request("updatesaldo\n"+String.valueOf(saldo)+"@"+username+"]"))
                result = connection.receive();
            if(!result.equals("Failed"))
                return true;
            else
                return false;
        } catch (Exception e) {
            Log.e("UtenteDAOImpl -> updateSaldoDiUtente -> ", "Errore: " + e.getMessage() );
            return false;
        }
    }
}
