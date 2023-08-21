package com.code.progettolso22_23.dao.dao_implementations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.code.progettolso22_23.dao.SocketInitializer;
import com.code.progettolso22_23.dao.dao_interfaces.BevandaDAO;
import com.code.progettolso22_23.entities.Assemblata;
import com.code.progettolso22_23.entities.Bevanda;

import java.util.ArrayList;
import java.util.List;

public class BevandaDAOImpl implements BevandaDAO {

    private SocketInitializer connection = SocketInitializer.getIstance();

    @Override
    public List<Bevanda> getAllBevande() {
        List<Bevanda> result = new ArrayList<>();
        String message;
        Bevanda tmp = new Bevanda();
        Assemblata tmpAssemblata = new Assemblata();
        try {
            if(connection.request("getBevande]")) {
                message = connection.receive();
                if (message.equals("Fallimento"))
                    return null;
            }
            else
                return null;
            char[] bevandeString = new char[message.length() + 1];
            //bevandeString = message.toCharArray();
            for(int i=0; i<message.length(); i++){
                bevandeString[i] = message.charAt(i);
            }
            bevandeString[bevandeString.length - 1] = '\n';
            int i = 0;
            int k = 0;
            int j = 0;
            int num = 1;
            Log.d("BevandaDAOImpl -> getAllBevande -> ", "messaggio: " + message);
            Log.d("BevandaDAOImpl -> getAllBevande -> ", "stringa bevande: " + bevandeString.toString());
            for(i = 0; i < bevandeString.length; i++) {
                if(bevandeString[i] == '@' && tmp.getNome() == null && num == 1) {
                    tmp.setNome(message.substring(k, i));
                    Log.d("BevandaDAOImpl -> getAllBevande -> ", "nome: " + message.substring(k, i));
                    k = i + 1;
                    num++;
                }
                else if(bevandeString[i] == '@' && tmp.getTipo() == null && num == 2) {
                    tmp.setTipo(message.substring(k, i));
                    Log.d("BevandaDAOImpl -> getAllBevande -> ", "tipo: " + message.substring(k, i));
                    k = i + 1;
                    num++;
                }
                else if(bevandeString[i] == '@' && tmp.getFoto() == null && num == 3) {
                    if(!(k == i)) {
                        tmp.setFoto(StringToBitMap(message.substring(k, i)));
                        Log.d("BevandaDAOImpl -> getAllBevande -> ", "foto: " + message.substring(k, i));
                    }
                    k = i + 1;
                    num++;
                }
                else if(bevandeString[i] == '@' && tmp.getNumeroVendite()==-1 && num == 4) {
                    tmp.setNumeroVendite(Integer.valueOf(message.substring(k, i)));
                    Log.d("BevandaDAOImpl -> getAllBevande -> ", "nunmero vendite: " + message.substring(k, i));
                    k = i + 1;
                    num++;
                }
                else if(bevandeString[i] == '@' && tmp.getCosto()==-1 && num == 5) {
                    tmp.setCosto(Float.valueOf(message.substring(k, i)));
                    Log.d("BevandaDAOImpl -> getAllBevande -> ", "costo: " + message.substring(k, i));
                    k = i + 1;
                    num++;
                }
                else if(bevandeString[i] == '@' && num == 6) {
                    if(!(k == i)) {
                        convertBevandaToAssemblata(tmpAssemblata, tmp);
                        tmpAssemblata.setDescrizione(message.substring(k, i));
                        Log.d("BevandaDAOImpl -> getAllBevande -> ", "descrizione: " + message.substring(k, i));
                    }
                    k = i + 1;
                    num++;
                }
                else if((bevandeString[i] == '-' || bevandeString[i] == '\n') && num == 7) {
                    Log.d("BevandaDAOImpl -> getAllBevande -> ", "ingredienti prima di k: " );
                    if (!(k == i)) {
                        Log.d("BevandaDAOImpl -> getAllBevande -> ", "ingredienti dopo di k: " );
                        convertBevandaToAssemblata(tmpAssemblata, tmp);
                        tmpAssemblata.setIngredienti(new ArrayList<>());
                        for (j = k; j < i; j++) {
                            if (bevandeString[j] == ';') {
                                String s = message.substring(k, j);
                                tmpAssemblata.getIngredienti().add(s);
                                Log.d("BevandaDAOImpl -> getAllBevande -> ", "ingredienti: " + message.substring(k, j));
                                k = j + 1;
                            }
                        }
                    }
                    if (tmpAssemblata.getNome() == null) {
                        result.add(tmp);
                        //tmp = new Bevanda();
                    } else {
                        result.add(tmpAssemblata);
                        Log.d("BevandaDAOImpl -> getAllBevande -> ", "ingrediente1: " + tmpAssemblata.getIngredienti().get(0));
                        Log.d("BevandaDAOImpl -> getAllBevande -> ", "ingrediente2 " + tmpAssemblata.getIngredienti().get(1));
                        Log.d("BevandaDAOImpl -> getAllBevande -> ", "ingrediente3 " + tmpAssemblata.getIngredienti().get(2));
                        //tmpAssemblata = new Assemblata();
                    }
                    tmp = new Bevanda();
                    tmpAssemblata = new Assemblata();
                    k = i + 1;
                    num = 1;
                }
            }
            return result;

        } catch (Exception e) {
            Log.e("BevandaDAOImpl -> getAllBevande -> ", "Errore: " + e.getMessage() );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateVenditeBevanda(String nome, int numero) {
        String result = null;
        try {
            if(connection.request("updatevenditabevanda\n"+String.valueOf(numero)+"@"+nome+"]"))
                result = connection.receive();
            if(!result.equals("Fallimento"))
                return true;
            else
                return false;
        } catch (Exception e) {
            Log.e("BevandaDAOImpl -> updateVenditeBevanda -> ", "Errore: " + e.getMessage() );
            return false;
        }
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            Log.d("BevandaDAOImpl -> StringToBitMap", "Fallimento");
            Log.d("BevandaDAOImpl -> StringToBitMap", e.getMessage());
            return null;
        }
    }

    public void convertBevandaToAssemblata(Assemblata a, Bevanda b) {
        a.setNome(b.getNome());
        a.setTipo(b.getTipo());
        a.setFoto(b.getFoto());
        a.setNumeroVendite(b.getNumeroVendite());
        a.setCosto(b.getCosto());
    }



}
