package com.code.progettolso22_23;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.code.progettolso22_23.R;
import com.code.progettolso22_23.gui.HomePage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //intent alla nuova pagina di benvenuto
        Intent apriPrimaPagina = new Intent(this, HomePage.class);
        startActivity(apriPrimaPagina);
        this.finish();
    }

}
