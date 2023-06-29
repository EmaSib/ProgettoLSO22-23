package com.code.progettolso22_23.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.MainController;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private MainController mainController = MainController.getIstance();

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private NavigationView navView;

    private AlertDialog.Builder ricercaDialog;

    private Button generiche;
    private Button caffetteria;
    private Button drink;
    private Button drink_analcolici;
    private Button frullati;
    private Button ricerca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        mainController.setUser(getIntent().getStringExtra("USERNAME"));

        //toolbar = findViewById(R.id.toolbar_homepage);
        //setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        navView = (NavigationView) findViewById(R.id.navigationView_homepage);
        navView.bringToFront();
        navView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        ricercaDialog = new AlertDialog.Builder(this);

        generiche = (Button) findViewById(R.id.genericheButton);
        generiche.setOnClickListener(this::onClick);
        caffetteria = (Button) findViewById(R.id.caffetteriaButton);
        caffetteria.setOnClickListener(this::onClick);
        drink = (Button) findViewById(R.id.drinkButton);
        drink.setOnClickListener(this::onClick);
        drink_analcolici = (Button) findViewById(R.id.drink_analcoliciButton);
        drink_analcolici.setOnClickListener(this::onClick);
        frullati = (Button) findViewById(R.id.frullatiButton);
        frullati.setOnClickListener(this::onClick);
        ricerca = (Button) findViewById(R.id.ricerca_ingredientiButton);
        ricerca.setOnClickListener(this::onClick);

        if(MainController.getConnectionType(this)==0) {
            Toast.makeText(this, "Connessione assente, impossibile continuare", Toast.LENGTH_SHORT).show();
        }
        mainController.inizializzaAllBevande();
        Intent i = getIntent();
        mainController.setUser(i.getStringExtra("USERNAME"));

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
            case R.id.carrello:
                Intent apriPaginaCarrello = new Intent(this, Carrello.class);
                startActivity(apriPaginaCarrello);
                this.onPause();
                break;
            case R.id.portafoglio:
                Intent apriSaldo = new Intent(this, Saldo.class);
                startActivity(apriSaldo);
                this.onPause();
                break;
            case R.id.logout:
                Intent apriPaginaLogin = new Intent(this, Login.class);
                startActivity(apriPaginaLogin);
                this.finish();
                break;
        }
        return false;
    }

    private void onClick(View view) {
        Intent apriPaginaListaBevande = new Intent(this, ListaBevande.class);
        switch (view.getId()) {
            case R.id.genericheButton:
                apriPaginaListaBevande.putExtra("TIPO", "Generica");
                startActivity(apriPaginaListaBevande);
                this.onPause();
                break;
            case R.id.caffetteriaButton:
                apriPaginaListaBevande.putExtra("TIPO", "Caffetteria");
                startActivity(apriPaginaListaBevande);
                this.onPause();
                break;
            case R.id.drinkButton:
                apriPaginaListaBevande.putExtra("TIPO", "Drink");
                startActivity(apriPaginaListaBevande);
                this.onPause();
                break;
            case R.id.drink_analcoliciButton:
                apriPaginaListaBevande.putExtra("TIPO", "Drink analcolico");
                startActivity(apriPaginaListaBevande);
                this.onPause();
                break;
            case R.id.frullatiButton:
                apriPaginaListaBevande.putExtra("TIPO", "Frullato");
                startActivity(apriPaginaListaBevande);
                this.onPause();
                break;
            case R.id.ricerca_ingredientiButton:
                inizializzaRicercaDialog(ricercaDialog);
                ricercaDialog.show();
                break;
        }
    }

    private void inizializzaRicercaDialog(AlertDialog.Builder ricercaDialog) {
        View ricercaDialogLayout = this.getLayoutInflater().inflate(R.layout.ricerca_dialog, null);
        ricercaDialog.setView(ricercaDialogLayout);
        ricercaDialog.setTitle("Seleziona ingredienti da ricercare");
        CheckBox campari = (CheckBox) ricercaDialogLayout.findViewById(R.id.campariCheckBox);
        //campari.setChecked(false);
        CheckBox martini = (CheckBox) ricercaDialogLayout.findViewById(R.id.martiniCheckBox);
        //martini.setChecked(false);
        CheckBox mango = (CheckBox) ricercaDialogLayout.findViewById(R.id.mangoCheckBox);
        //mango.setChecked(false);
        CheckBox menta = (CheckBox) ricercaDialogLayout.findViewById(R.id.mentaCheckBox);
        //menta.setChecked(false);
        CheckBox bitter = (CheckBox) ricercaDialogLayout.findViewById(R.id.bitterCheckBox);
        //bitter.setChecked(false);

        ricercaDialog.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        ricercaDialog.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(campari.isChecked())
                    mainController.aggiungiIngrediente("Campari");
                if(martini.isChecked())
                    mainController.aggiungiIngrediente("Martini");
                if(mango.isChecked())
                    mainController.aggiungiIngrediente("Mango");
                if(menta.isChecked())
                    mainController.aggiungiIngrediente("Menta");
                if(bitter.isChecked())
                    mainController.aggiungiIngrediente("Bitter");
                if(mainController.getIngredienti().size()==0) {
                    Toast.makeText(HomePage.this, "Nessun ingrediente selezionato, impossibile ricercare", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
                else {
                    dialogInterface.dismiss();
                    passaAListaBevande();

                }
            }
        });


    }

    public void passaAListaBevande() {
        Intent apriPaginaListaBevande = new Intent(this, ListaBevande.class);
        apriPaginaListaBevande.putExtra("TIPO", "Ricerca");
        startActivity(apriPaginaListaBevande);
        this.onPause();
    }

}
