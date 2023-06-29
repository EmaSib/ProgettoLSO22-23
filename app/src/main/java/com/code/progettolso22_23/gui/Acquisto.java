package com.code.progettolso22_23.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.CarrelloController;
import com.code.progettolso22_23.utils.ListViewAdapterCarrello;

public class Acquisto extends AppCompatActivity {

    private CarrelloController carrelloController = CarrelloController.getIstance();

    private Button confermaButton;

    private Button annullaButton;

    private ListView ListaBevande;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acquisto);

        confermaButton = (Button) findViewById(R.id.confermabutton);
        confermaButton.setOnClickListener(this::onClick);

        annullaButton = (Button) findViewById(R.id.annullabutton);
        annullaButton.setOnClickListener(this::onClick);

        ListaBevande = (ListView) findViewById(R.id.list_view_lista_acquisto);
        updateListView();
    }

    private void updateListView() {
        ListaBevande.setAdapter(new ListViewAdapterCarrello(this, carrelloController.ottieniBevandeDaCarrello(), carrelloController.ottieniCostoBevandaDaCarrello(), carrelloController.ottieniQuantitaDaCarrello()));
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.confermabutton:
                carrelloController.svuotaCarrello();
                this.finish();
                break;
            case R.id.annullabutton:
                Intent ritornaPaginaCarrello = new Intent(this, Carrello.class);
                startActivity(ritornaPaginaCarrello);
                this.finish();
                break;
        }
    }

}
