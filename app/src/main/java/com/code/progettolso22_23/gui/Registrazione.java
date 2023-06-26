package com.code.progettolso22_23.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.controls.LogController;

public class Registrazione extends AppCompatActivity {

    private LogController logController = LogController.getInstance();

    private String Username;

    private String Password;

    private String Nome;

    private String Cognome;

    private Button registrati;

    private EditText UsernameField;

    private EditText PasswordField;

    private EditText NomeField;

    private EditText CognomeField;

    private AlertDialog.Builder SignUpFallito;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);

        registrati = (Button) findViewById(R.id.registratiButton);
        registrati.setOnClickListener(this::onClick);

        UsernameField = (EditText) findViewById(R.id.username_textfield);
        PasswordField = (EditText) findViewById(R.id.password_textfield);
        NomeField = (EditText) findViewById(R.id.nome_textfield);
        CognomeField = (EditText) findViewById(R.id.cognome_textfield);

        SignUpFallito = new AlertDialog.Builder( this);
    }

    private void onClick(View view) {
        boolean successful = insertCheckCredentials();
        if(successful) {
            //TODO
            //intent a HomePage o Login
            this.finish();
        }
    }

    private boolean insertCheckCredentials() {
        boolean success=false;
        Username = UsernameField.getText().toString();
        Password = PasswordField.getText().toString();
        Nome = NomeField.getText().toString();
        Cognome = CognomeField.getText().toString();

        if (Username.length()<=0 || Password.length()<=0 || Nome.length()<=0 || Cognome.length()<=0){
            Toast.makeText(this, "Riempire tutti i campi. Riprova!", Toast.LENGTH_LONG).show();
        } else {
            boolean successful = logController.effettuaSignUp(Username, Password, Nome, Cognome);
            if(successful){
                success = true;
            }
            else {
                SignUpFallito.setTitle("Registrazione non Riuscita!");
                SignUpFallito.setMessage("Qualcosa è andato storto!\nRiprova...");
                SignUpFallito.setCancelable(true);
                SignUpFallito.show();
                UsernameField.setText("");
                PasswordField.setText("");
                NomeField.setText("");
                CognomeField.setText("");
            }
        }

        return success;

    }

}
