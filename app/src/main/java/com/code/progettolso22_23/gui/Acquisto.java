package com.code.progettolso22_23.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.CarrelloController;
import com.code.progettolso22_23.utils.ListViewAdapterCarrello;
import com.code.progettolso22_23.controls.MainController;

public class Acquisto extends AppCompatActivity {

    private CarrelloController carrelloController = CarrelloController.getIstance();

    private MainController mainController = MainController.getIstance();

    private Button confermaButton;

    private Button annullaButton;

    private TextView totaleTextView;

    private TextView saldoTextView;

    private ListView ListaBevande;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acquisto);

        confermaButton = (Button) findViewById(R.id.confermabutton);
        confermaButton.setOnClickListener(this::onClick);

        annullaButton = (Button) findViewById(R.id.annullabutton);
        annullaButton.setOnClickListener(this::onClick);

        totaleTextView = (TextView) findViewById(R.id.textViewImporto);
        totaleTextView.setText(String.valueOf(carrelloController.ottieniPrezzoTotale()));

        saldoTextView = (TextView) findViewById(R.id.textViewSaldoDisponibile);
        saldoTextView.setText(String.valueOf(mainController.getSaldoByUsername()));

        ListaBevande = (ListView) findViewById(R.id.list_view_lista_acquisto);
        updateListView();
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.confermabutton:
                float saldo = Float.valueOf((String) saldoTextView.getText());
                float totale = Float.valueOf((String) totaleTextView.getText());
                if(saldo == -1) {
                    Toast.makeText(this, "Impossibile completare l'operazione, errore con il server. Riavvia l'applicativo", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(totale < saldo){

                    if(mainController.updateSaldoDiUtente(saldo - totale)) {
                        if (carrelloController.updateVenditeBevandeAcquistate()) {
                            carrelloController.svuotaCarrello();
                            Toast.makeText(this, "Operazione riuscita!", Toast.LENGTH_SHORT).show();
                            this.finish();
                        }
                        else
                            Toast.makeText(this, "Impossibile completare l'operazione, errore con il server. Riavvia l'applicativo", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(this, "Impossibile completare l'operazione, errore con il server. Riavvia l'applicativo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Saldo non sufficiente.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.annullabutton:
                Intent ritornaPaginaCarrello = new Intent(this, Carrello.class);
                startActivity(ritornaPaginaCarrello);
                this.finish();
                break;
        }
    }

    private void updateListView() {
        ListaBevande.setAdapter(
                new ListViewAdapterCarrello(this, carrelloController.ottieniBevandeDaCarrello(), carrelloController.ottieniCostoBevandaDaCarrello(), carrelloController.ottieniQuantitaDaCarrello()));
    }

}
