package com.code.progettolso22_23.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.MainController;
import com.code.progettolso22_23.entities.Assemblata;
import com.code.progettolso22_23.entities.Bevanda;
import com.code.progettolso22_23.utils.ListViewAdapterBevandaAssemblata;
import com.code.progettolso22_23.utils.ListViewAdapterBevandaNormale;

import java.util.ArrayList;
import java.util.List;

public class ListaBevande extends AppCompatActivity {

    private MainController mainController = MainController.getIstance();
    private String BevandeDaMostrare;
    private List<Bevanda> bevande;
    private List<Bevanda> consigliate;

    private ListView listViewBevande;
    private ListView listViewBevandeConsigliate;
    private Button tornaIndietro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_bevande);

        Intent i = getIntent();
        BevandeDaMostrare = i.getStringExtra("TIPO");
        bevande = mainController.restituisciBevandeDiUnTipo(BevandeDaMostrare);
        consigliate = mainController.trovaConsigliatiDaLista(bevande);

        tornaIndietro = (Button) findViewById(R.id.torna_indietro_button_lista_bevande);
        tornaIndietro.setOnClickListener(this::onClick);

        listViewBevande = (ListView) findViewById(R.id.list_view_lista_bevande);
        listViewBevande.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                String nome = value;
                //TODO
                //controllare se nome corrisponde a tutta la linea o solo al nome della bevanda
                passaAPaginaBevanda(nome);
            }
        });
        listViewBevandeConsigliate = (ListView) findViewById(R.id.list_view_lista_bevande);
        listViewBevandeConsigliate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                String nome = value;
                //TODO
                //controllare se nome corrisponde a tutta la linea o solo al nome della bevanda
                passaAPaginaBevanda(nome);
            }
        });
        updateListView();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.torna_indietro_button_lista_bevande:
                Intent apriPaginaHomePage = new Intent(this, HomePage.class);
                startActivity(apriPaginaHomePage);
                this.finish();
                break;
        }
    }

    private void updateListView() {
        if(bevande.get(0).getClass().equals(Assemblata.class)) {
            listViewBevande.setAdapter(new ListViewAdapterBevandaAssemblata(this, mainController.getNomiBevandeComeArray(bevande), mainController.getCostiBevandeComeArray(bevande), mainController.getIngredientiBevandeComeArray(bevande)));
            listViewBevandeConsigliate.setAdapter(new ListViewAdapterBevandaAssemblata(this, mainController.getNomiBevandeComeArray(consigliate), mainController.getCostiBevandeComeArray(consigliate), mainController.getIngredientiBevandeComeArray(consigliate)));
        } else {
            listViewBevande.setAdapter(new ListViewAdapterBevandaNormale(this, mainController.getNomiBevandeComeArray(bevande), mainController.getCostiBevandeComeArray(bevande)));
            listViewBevandeConsigliate.setAdapter(new ListViewAdapterBevandaNormale(this, mainController.getNomiBevandeComeArray(consigliate), mainController.getCostiBevandeComeArray(consigliate)));
        }
    }

    private void passaAPaginaBevanda(String nome) {
        Intent apriPaginaBevanda = new Intent(this, ListaBevande.class);
        apriPaginaBevanda.putExtra("BEVANDA", nome);
        startActivity(apriPaginaBevanda);
        this.onPause();
    }

}
