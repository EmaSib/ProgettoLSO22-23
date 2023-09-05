package com.code.progettolso22_23.gui;

import android.content.Intent;
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

public class Login extends AppCompatActivity {

    private LogController logController = LogController.getInstance();

    private String Username;
    private String Password;
    private Button accedi;
    private Button registrati;
    private EditText UsernameField;
    private EditText PasswordField;
    private AlertDialog.Builder LoginFallito;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accedi = (Button) findViewById(R.id.accediButton);
        accedi.setOnClickListener(this::onClick);
        registrati = (Button) findViewById(R.id.registrazioneButton);
        registrati.setOnClickListener(this::onClick);

        UsernameField = (EditText) findViewById(R.id.username_textfield);
        PasswordField = (EditText) findViewById(R.id.password_textfield);

        LoginFallito = new AlertDialog.Builder( this);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.accediButton:
                boolean succesLogIn = insertCheckCredentials();
                if (succesLogIn) {
                    Intent apriPaginaHomepage = new Intent(this, HomePage.class);
                    apriPaginaHomepage.putExtra("USERNAME", Username);
                    startActivity(apriPaginaHomepage);
                    this.finish();
                }
                break;
            case R.id.registrazioneButton:
                Intent apriPaginaRegistrazione = new Intent(this, Registrazione.class);
                startActivity(apriPaginaRegistrazione);
                this.finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean insertCheckCredentials() {
        boolean success=false;
        Username = UsernameField.getText().toString();
        Password = PasswordField.getText().toString();

        if (Username.length()<=0 && Password.length()<=0){
            Toast.makeText(this, "Username e Password nulli. Riprova!", Toast.LENGTH_LONG).show();
        } else if (Username.length()<=0) {
            Toast.makeText(this, "Username non inserito. Riprova!", Toast.LENGTH_LONG).show();
            PasswordField.setText("");
        } else if (Password.length()<=0) {
            Toast.makeText(this, "Password non inserita. Riprova!", Toast.LENGTH_LONG).show();
            UsernameField.setText("");
        } else {
            int successful = logController.checkLogin(Username, Password);
            if(successful == 1){
                success = true;
            }
            else if(successful==0){
                LoginFallito.setTitle("Login non Riuscito!");
                LoginFallito.setMessage("Le credenziali inserite non sono corrette!\nRiprova...");
                LoginFallito.setCancelable(true);
                LoginFallito.show();
                UsernameField.setText("");
                PasswordField.setText("");
            }
            else if(successful==2){
                LoginFallito.setTitle("Login non Riuscito!");
                LoginFallito.setMessage("Il server non accetta nuove connessioni!\nRiprova tra qualche momento...");
                LoginFallito.setCancelable(true);
                LoginFallito.show();
                UsernameField.setText("");
                PasswordField.setText("");
                logController.closeConnection();
            }
            else {
                LoginFallito.setTitle("Login non Riuscito!");
                LoginFallito.setMessage("Il server non risponde!\nRiavvia l'applicativo e riprova tra qualche momento...");
                LoginFallito.setCancelable(true);
                LoginFallito.show();
                UsernameField.setText("");
                PasswordField.setText("");
            }
        }

        return success;

    }

}
