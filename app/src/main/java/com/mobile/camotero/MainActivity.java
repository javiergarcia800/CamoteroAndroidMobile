package com.mobile.camotero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se oculta la barra superior para la pantalla de logín.
        super.getSupportActionBar().hide();
    }
}
