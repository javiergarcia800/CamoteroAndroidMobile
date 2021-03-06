package com.mobile.camotero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se oculta la barra de estatus.
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Se oculta la barra del action.
        super.getSupportActionBar().hide();

        findViews();

        events();

        saveTokenNotifications();
    }



    private void findViews() {
        editTextEmail = super.findViewById(R.id.editTextMail);
        editTextPassword = super.findViewById(R.id.editTextPassword);

        buttonIniciarSesion = super.findViewById(R.id.btnIniciarSesion);
    }

    private void events() {
        buttonIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.btnIniciarSesion:
                doToLogin();
                break;
            /*case R.id.textViewGoSignUp:
                goToSignUp();
                break;*/
        }
    }

    private void doToLogin() {

        // Se realiza la validación de correo y password;
        if (editTextEmail.getText().toString().equals(editTextPassword.getText().toString())) {
            loginOk();
        } else {
            Log.i("CAMOTERO", "Contraseña incorrecta.");
            Toast.makeText(MainActivity.this, "Correo y/o contraseña incorrecta!", Toast.LENGTH_SHORT).show();
        }

    }

    private void loginOk() {
        // Se cambia de actividad.
        Intent i = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(i);

        // Se destruye el activity actual.
        finish();
    }

    private void saveTokenNotifications() {

        Log.i("CAMOTERO", "METODO saveTokenNotifications()..............");

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("CAMOTERO", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();


                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("CAMOTERO", "El TOKEN generado es: " + token);

                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
