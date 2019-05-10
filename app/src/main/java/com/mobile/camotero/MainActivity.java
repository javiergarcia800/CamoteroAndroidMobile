package com.mobile.camotero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se oculta la barra de estatus.
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Se oculta la barra del action.
        super.getSupportActionBar().hide();
    }
}
