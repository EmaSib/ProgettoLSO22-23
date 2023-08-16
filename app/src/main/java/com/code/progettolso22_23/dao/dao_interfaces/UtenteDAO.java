package com.code.progettolso22_23.dao.dao_interfaces;

public interface UtenteDAO {

    int checkLogin(String username, String password);

    boolean effettuaSignUp(String username, String password, String nome, String cognome);

    float getSaldoByUsername(String username);

    boolean updateSaldoDiUtente(String username, float saldo);


}
