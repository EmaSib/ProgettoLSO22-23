package com.code.progettolso22_23.controls;

public class LogController {

    private static LogController instance = null;

    private LogController() {}

    public static LogController getInstance(){
        if(instance == null)
            instance = new LogController();
        return instance;
    }

    public boolean checkLogin(String username, String password) {
        //TODO
        return true;
    }

    public boolean effettuaSignUp(String Username, String Password, String Nome, String Cognome) {
        //TODO
        return true;
    }

}
