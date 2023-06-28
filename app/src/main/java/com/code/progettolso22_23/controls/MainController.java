package com.code.progettolso22_23.controls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.code.progettolso22_23.entities.Assemblata;
import com.code.progettolso22_23.entities.Bevanda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MainController {

    private static MainController istance = null;
    private String User;
    private List<String> ingredienti = new ArrayList<String>();
    private List<Bevanda> AllBevande = new ArrayList<>();
    private DAOController daoController;

    private MainController() {
        daoController = DAOController.getIstance();
    }

    public static MainController getIstance() {
        if(istance == null)
            istance = new MainController();
        return istance;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public List<Bevanda> getAllBevande() {
        return AllBevande;
    }

    public void setAllBevande(List<Bevanda> allBevande) {
        AllBevande = allBevande;
    }

    public void aggiungiIngrediente(String string) {
        ingredienti.add(string);
    }

    public List<String> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<String> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public static int getConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            return 1;
        } else {
            return 0;
        }
    }

        /*int result = 0; // Ritorna i tipi di connessione. 0: nessuna; 1: rete dati; 2: wifi; 3: vpn
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        result = 3;
                    }
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connesso a internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = 2;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        result = 1;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_VPN) {
                        result = 3;
                    }
                }
            }
        }
        return result;
    }*/

    public void inizializzaAllBevande() {
        this.setAllBevande(daoController.ottieniTutteLeBevande());
    }

    public List<Bevanda> restituisciBevandeDiUnTipo(String tipo) {
        ArrayList<Bevanda> result = new ArrayList<>();
        if(tipo.equals("Ricerca")){
            for(Bevanda b : this.getAllBevande())  {
                if(b.getClass() == Assemblata.class) {
                    for(String i : this.getIngredienti())
                        if(((Assemblata) b).getIngredienti().contains(i))
                            result.add(b);
                }
            }
        } else {
            for(Bevanda b : this.getAllBevande()) {
                if(b.getTipo().equals(tipo))
                    result.add(b);
            }
        }
        return result;


    }

    public Bevanda trovaBevandaByNome(String nome) {
        Bevanda result = null;
        for (Bevanda b : this.getAllBevande())
            if(b.getNome().equals(nome))
                result = b;
        return result;
    }


    public ArrayList<Bevanda> trovaConsigliatiDaLista(List<Bevanda> lista) {
        ArrayList<Bevanda> result = new ArrayList<>();
        lista.sort(new Comparator<Bevanda>() {
            @Override
            public int compare(Bevanda bevanda, Bevanda t1) {
                if(bevanda.getNumeroVendite() > t1.getNumeroVendite())
                    return 1;
                else if(bevanda.getNumeroVendite() == t1.getNumeroVendite())
                    return 0;
                else
                    return -1;
            }
        });
        int i=0;
        if(lista.size() > 3) {
            for (Bevanda b : lista) {
                if (i < 3)
                    result.add(b);
                i++;
            }
        }
        else {
            for (Bevanda b : lista) {
                if (i < 1)
                    result.add(b);
                i++;
            }
        }
        return result;
    }

    public String[] getNomiBevandeComeArray(List<Bevanda> bevande) {
        String[] result = new String[bevande.size()];
        int i=0;
        for (Bevanda b : bevande) {
            result[i] = b.getNome();
            i++;
        }
        return result;
    }

    public String[] getCostiBevandeComeArray(List<Bevanda> bevande) {
        String[] result = new String[bevande.size()];
        int i=0;
        for (Bevanda b : bevande) {
            result[i] = String.valueOf(b.getCosto());
            i++;
        }
        return result;
    }

    public String[] getIngredientiBevandeComeArray(List<Bevanda> bevande) {
        String[] result = new String[bevande.size()];
        int i=0;
        String tmp = "";
        for (Bevanda b : bevande) {
            Assemblata a = (Assemblata) b;
            tmp="";
            for (String s : a.getIngredienti()) {
                tmp.concat(s);
                tmp.concat(" - ");
            }
            result[i] = tmp;
            i++;
        }
        return result;
    }

    public float getSaldoByUsername() {

        return daoController.getSaldoByUsername(User);

    }

    public void updateSaldoDiUtente(float saldo) {

        daoController.updateSaldoDiUtente(User, saldo);

    }




}
