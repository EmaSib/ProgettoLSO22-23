package com.code.progettolso22_23.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.MainController;

public class Saldo extends AppCompatActivity {

    private MainController mainController = MainController.getIstance();

    private float saldo;

    private TextView saldoTextView;
    private Button tornaIndietro;
    private Button ricarica;
    private EditText daRicaricare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saldo);

        saldoTextView = (TextView) findViewById(R.id.saldoid);
        tornaIndietro = (Button) findViewById(R.id.torna_indietro);
        tornaIndietro.setOnClickListener(this::onClick);
        ricarica = (Button) findViewById(R.id.ricarica);
        ricarica.setOnClickListener(this::onClick);
        daRicaricare = (EditText) findViewById(R.id.editTextNumber);

        saldo = mainController.getSaldoByUsername();
        if(saldo == -1)
            Toast.makeText(this, "Impossibile visualizzare saldo, errore con il server!\nRiprova tra qualche momento...", Toast.LENGTH_SHORT).show();
        saldoTextView.setText(String.valueOf(saldo));

    }

    private void onClick(View view) {

        switch (view.getId()) {
            case R.id.torna_indietro:
                this.finish();
                break;
            case R.id.ricarica:
                if(daRicaricare.getText().equals("") || Float.valueOf(String.valueOf(daRicaricare.getText())) <= 0 ) {
                    Toast.makeText(this, "Impossibile ricaricare il saldo, riprova!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(mainController.updateSaldoDiUtente(saldo + Float.valueOf(String.valueOf(daRicaricare.getText())))) {
                        Toast.makeText(this, "Saldo ricaricato con successo!", Toast.LENGTH_SHORT).show();
                        saldo = saldo + Float.valueOf(String.valueOf(daRicaricare.getText()));
                        saldoTextView.setText(String.valueOf(saldo));
                    }
                    else
                        Toast.makeText(this, "Impossibile ricaricare il saldo.\n Riprova tra qualche momento...", Toast.LENGTH_SHORT).show();
                }
                daRicaricare.setText("");
                break;
        }

    }


}
