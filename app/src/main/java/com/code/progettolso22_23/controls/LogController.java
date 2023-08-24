package com.code.progettolso22_23.controls;

public class LogController {

    private DAOController daoController = null;
    private static LogController instance = null;

    private LogController() {}

    public static LogController getInstance(){
        if(instance == null)
            instance = new LogController();
        return instance;
    }

    public int checkLogin(String username, String password) {
        daoController = DAOController.getIstance();
        return daoController.checkLogin(username, password);
    }

    public boolean effettuaSignUp(String Username, String Password, String Nome, String Cognome) {
        daoController = DAOController.getIstance();
        return daoController.effettuaSignUp(Username, Password, Nome, Cognome);
    }

    public void closeConnection(){

        daoController.closeConnection();

    }

}
