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
            if(connection.request("getBevande"))
                message = connection.receive();
            else
                return null;
            char[] bevandeString;
            bevandeString = message.toCharArray();
            int i = 0;
            int k = 0;
            int j = 0;
            for(i = 0; i < bevandeString.length; i++) {
                if(bevandeString[i] == '\n') {
                    tmp.setNome(message.substring(k, i - 1));
                    k = i + 1;
                }
                else if(bevandeString[i] == '@' && tmp.getTipo() == null) {
                    tmp.setTipo(message.substring(k, i - 1));
                    k = i + 1;
                }
                else if(bevandeString[i] == '@' && tmp.getFoto() == null) {
                    tmp.setFoto(StringToBitMap(message.substring(k, i - 1)));
                    k = i + 1;
                }
                else if(bevandeString[i] == '@' && tmp.getNumeroVendite()==-1) {
                    tmp.setNumeroVendite(Integer.valueOf(message.substring(k, i - 1)));
                    k = i + 1;
                }
                else if(bevandeString[i] == '@' && tmp.getCosto()==-1) {
                    tmp.setCosto(Float.valueOf(message.substring(k, i - 1)));
                    k = i + 1;
                }
                else if(bevandeString[i] == '@') {
                    if(!message.substring(k, i - 1).equals("NULL")) {
                        convertBevandaToAssemblata(tmpAssemblata, tmp);
                        tmpAssemblata.setDescrizione(message.substring(k, i - 1));
                    }
                    k = i + 1;
                }
                else if(bevandeString[i] == '\n') {
                    if(!message.substring(k, i - 1).equals("NULL")) {
                        convertBevandaToAssemblata(tmpAssemblata, tmp);
                        tmpAssemblata.setIngredienti(new ArrayList<>());
                        for (j = k; j < i; j++) {
                            if (bevandeString[j] == ';') {
                                tmpAssemblata.getIngredienti().add(message.substring(k, j - 1));
                                k = j + 1;
                            }
                        }
                    }
                }
                if(tmpAssemblata.getNome().equals(null))
                    result.add(tmp);
                else
                    result.add(tmpAssemblata);
                k = i;
            }
            return result;

        } catch (Exception e) {
            Log.e("BevandaDAOImpl -> getAllBevande -> ", "Errore: " + e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean updateVenditeBevanda(String nome, int numero) {
        String result = null;
        try {
            if(connection.request("updatevenditabevanda\n"+nome+"@"+String.valueOf(numero)))
                result = connection.receive();
            if(result.equals("Success"))
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
