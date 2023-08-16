package com.code.progettolso22_23.dao.dao_interfaces;

import com.code.progettolso22_23.entities.Bevanda;

import java.util.List;

public interface BevandaDAO {

    List<Bevanda> getAllBevande();

    boolean updateVenditeBevanda(String nome, int numero);

}
