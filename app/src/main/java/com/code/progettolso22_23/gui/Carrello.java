package com.code.progettolso22_23.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.CarrelloController;
import com.code.progettolso22_23.controls.MainController;
import com.code.progettolso22_23.utils.ListViewAdapterCarrello;

public class Carrello extends AppCompatActivity {

    private CarrelloController carrelloController = CarrelloController.getIstance();
    private MainController mainController = MainController.getIstance();

    private String BevandaScelta;

    private Button acquistaButton;
    private ImageButton backButton;
    private ListView ListaCarrello;

    private AlertDialog.Builder ModificaCarrello;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello);

        BevandaScelta="";

        acquistaButton = (Button) findViewById(R.id.acquistabutton);
        acquistaButton.setOnClickListener(this::onClick);

        backButton = (ImageButton) findViewById(R.id.backButton2);
        backButton.setOnClickListener(this::onClick);

        ListaCarrello = (ListView) findViewById(R.id.list_view_lista_carrello);
        ListaCarrello.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                String nome = value;
                BevandaScelta = nome;
                apriDialog();
            }
        });
        updateListView();

        ModificaCarrello = new AlertDialog.Builder(this);
    }

    private void updateListView() {
        ListaCarrello.setAdapter(new ListViewAdapterCarrello(this, carrelloController.ottieniBevandeDaCarrello(), carrelloController.ottieniCostoBevandaDaCarrello(), carrelloController.ottieniQuantitaDaCarrello()));
    }

    private void apriDialog() {
        inizializzaModificaDialog(ModificaCarrello);
        ModificaCarrello.show();
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.acquistabutton:
                Intent apriPaginaAcquisto = new Intent(this, Acquisto.class);
                startActivity(apriPaginaAcquisto);
                this.finish();
                break;
            case R.id.backButton2:
                this.finish();
                break;
        }
    }

    private void inizializzaModificaDialog(AlertDialog.Builder modificaDialog) {
        View modificaDialogLayout = this.getLayoutInflater().inflate(R.layout.modifica_carrello_dialog, null);
        modificaDialog.setView(modificaDialogLayout);
        modificaDialog.setTitle("Modifica carrello");

        final int[] selectedValue = new int[1];
        NumberPicker picker = (NumberPicker) modificaDialogLayout.findViewById(R.id.number_picker_quantita);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                selectedValue[0] = i1;
            }
        });
        picker.setMinValue(0);
        picker.setMaxValue(100);

        modificaDialog.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        modificaDialog.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carrelloController.aggiornaQuantitaBevandaCarrello(BevandaScelta, selectedValue[0]);
                updateListView();
                dialogInterface.dismiss();
            }
        });


    }
}
