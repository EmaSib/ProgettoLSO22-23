package com.code.progettolso22_23.dao;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SocketInitializer {

    private static SocketInitializer istance = null;
    private Socket socket;
    private PrintStream outMessage;
    private BufferedReader inMessage;
    private String IPAddress="127.0.0.1";
    private int PortNumber=5432;

    private SocketInitializer() {
        try {
            socket = new Socket(IPAddress, PortNumber);
            outMessage = new PrintStream(socket.getOutputStream());
            inMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            Log.e("SocketInitializer -> Costruttore -> ", "Errore creazione socket: " + e.getMessage() );
        }
    }

    public static SocketInitializer getIstance() {
        if(istance==null)
            istance = new SocketInitializer();
        return istance;
    }

    public boolean request(String message) throws Exception {
        if(socket.isClosed() || !socket.isConnected())
            istance = new SocketInitializer();
        boolean result;
        try {
            outMessage.println(message);
            result = true;
        } catch (Exception e) {
            Log.e("SocketInitializer -> request -> ", "Errore invio messaggio: " + e.getMessage() );
            throw new Exception();
        }
        return result;
    }

    public String receive() throws Exception {
        if(socket.isClosed() || !socket.isConnected())
            istance = new SocketInitializer();
        String result = null;
        try {
            result = inMessage.readLine();
        } catch (Exception e) {
            Log.e("SocketInitializer -> receive -> ", "Errore ricezione messaggio: " + e.getMessage() );
            throw new Exception();
        }
        return result;
    }

    public void closeSocket() {
        try {
            outMessage.close();
            inMessage.close();
            socket.close();
        } catch (IOException e) {
            Log.e("SocketInitializer -> closeSocket -> ", "Errore chiusura socket: " + e.getMessage() );
        }
    }

}
