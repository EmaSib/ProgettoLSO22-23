package com.code.progettolso22_23.dao;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketInitializer {

    private static SocketInitializer istance = null;
    private Socket socket;
    private PrintStream outMessage;
    private BufferedReader inMessage;
    private String IPAddress="ec2-18-193-38-228.eu-central-1.compute.amazonaws.com";
    private int PortNumber=5200;

    private SocketInitializer() {
        try {
            socket = new Socket(IPAddress, PortNumber);
            outMessage = new PrintStream(socket.getOutputStream());
            inMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            Log.e("SocketInitializer -> Costruttore -> ", "Errore creazione socket: " + e.getMessage() );
            e.printStackTrace();
        }
    }

    public static SocketInitializer getIstance() {
        if(istance==null)
            istance = new SocketInitializer();
        return istance;
    }

    public boolean request(String message) throws Exception {
        if(socket.isClosed()){
            reconnect();
        }
        boolean result;
        try {
            outMessage.println(message);
            result = true;
        } catch (Exception e) {
            Log.e("SocketInitializer -> request -> ", "Errore invio messaggio: " + e.getMessage() );
            e.printStackTrace();
            throw new Exception();
        }
        return result;
    }

    public String receive() throws Exception {
        if(socket.isClosed()){
            reconnect();
        }
        String result = null;
        try {
            socket.setSoTimeout(5 * 1000);
            result = inMessage.readLine();
        } catch (Exception e) {
            Log.e("SocketInitializer -> receive -> ", "Errore ricezione messaggio: " + e.getMessage() );
            e.printStackTrace();
            if(e.getClass().equals(SocketTimeoutException.class))
                socket.close();
            throw new Exception();
        }
        return result;
    }

    public void closeSocket() {
      try {
            socket.close();
        } catch (IOException e) {
            Log.e("SocketInitializer -> closeSocket -> ", "Errore chiusura socket: " + e.getMessage() );
        }
    }

    private void reconnect() {
        try {
            socket = new Socket(IPAddress, PortNumber);
            outMessage = new PrintStream(socket.getOutputStream());
            inMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            Log.e("SocketInitializer -> Reconnect -> ", "Errore creazione socket: " + e.getMessage() );
            e.printStackTrace();
        }
    }

}
