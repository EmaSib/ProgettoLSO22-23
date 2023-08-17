package com.code.progettolso22_23.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.gui.HomePage;
import com.code.progettolso22_23.gui.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //intent alla nuova pagina di benvenuto
        Intent apriPrimaPagina = new Intent(this, Login.class);
        startActivity(apriPrimaPagina);
        this.finish();
    }

}
