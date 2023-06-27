package com.code.progettolso22_23.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.CarrelloController;
import com.code.progettolso22_23.controls.MainController;
import com.code.progettolso22_23.entities.Assemblata;
import com.code.progettolso22_23.entities.Bevanda;
import com.code.progettolso22_23.utils.ListViewAdapterGenerico;

public class PaginaBevanda extends AppCompatActivity {

    private MainController mainController = MainController.getIstance();
    private CarrelloController carrelloController = CarrelloController.getIstance();

    private String NomeBevanda;
    private Bevanda BevandaScelta;
    private Assemblata BevandaSceltaAssemblata;

    private TextView Nome;
    private ImageView Foto;
    private TextView Costo;
    private TextView Ingredienti;
    private ListView ListaIngredienti;
    private TextView Descrizione;
    private Button TornaIndietro;
    private Button Aggiungi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_bevanda);

        NomeBevanda = getIntent().getStringExtra("BEVANDA");
        BevandaScelta = mainController.trovaBevandaByNome(NomeBevanda);

        Nome = (TextView) findViewById(R.id.nome_pagina_bevanda_text_view);
        Nome.setText(NomeBevanda);
        Foto = (ImageView) findViewById(R.id.foto_pagina_bevanda_image_view);
        Foto.setImageBitmap(BevandaScelta.getFoto());
        Costo = (TextView) findViewById(R.id.costo_pagina_bevanda_text_view);
        Costo.setText(String.valueOf(BevandaScelta.getCosto()));
        Ingredienti = (TextView) findViewById(R.id.ingredienti_pagina_bevanda_text_view);
        ListaIngredienti = (ListView) findViewById(R.id.lista_ingredienti_pagina_bevanda_list_view);
        Descrizione = (TextView) findViewById(R.id.descrizione_pagina_bevanda_text_view);
        TornaIndietro = (Button) findViewById(R.id.torna_indietro_button_pagina_bevanda);
        TornaIndietro.setOnClickListener(this::onClick);
        Aggiungi = (Button) findViewById(R.id.aggiungi_carrello_button_pagina_bevanda);
        Aggiungi.setOnClickListener(this::onClick);
        if(BevandaScelta.getClass().equals(Assemblata.class)) {
            BevandaSceltaAssemblata = (Assemblata) BevandaScelta;
            Descrizione.setText(BevandaSceltaAssemblata.getDescrizione());
            ListaIngredienti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
            updateListView();
        }
        else {
            Ingredienti.setText("");
            Descrizione.setText("");
        }

    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.aggiungi_carrello_button_pagina_bevanda:
                carrelloController.aggiungiAlCarrello(BevandaScelta);
                Toast.makeText(this, "Elemento aggiunto al carrello con successo!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.torna_indietro_button_pagina_bevanda:
                //Intent apriListaBevande = new Intent(this, ListaBevande.class);
                //startActivity(apriListaBevande);
                this.finish();
                break;
        }
    }

    public void updateListView() {

        ListaIngredienti.setAdapter(new ListViewAdapterGenerico(this, getArrayDaListaIngredienti()));


    }

    public String[] getArrayDaListaIngredienti() {

        String[] result = new String[BevandaSceltaAssemblata.getIngredienti().size()];
        int i=0;
        for(String s : BevandaSceltaAssemblata.getIngredienti())
            result[i] = s;
        return result;

    }



}
