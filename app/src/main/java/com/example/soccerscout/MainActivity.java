package com.example.soccerscout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerscout.db.DbAux;
import com.example.soccerscout.usuarios.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button registrarse;
    Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrarse = findViewById(R.id.botonRegistrarse);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });

        entrar = findViewById(R.id.botonEntrar);

        entrar.setOnClickListener(new View.OnClickListener() {

            TextInputEditText correo = findViewById(R.id.textoCorreo);
            EditText contra = findViewById(R.id.textoContra);
            @Override
            public void onClick(View view) {

                String adminCorreo = correo.getText().toString();
                String adminContra = contra.getText().toString();

                Toast.makeText(MainActivity.this, adminCorreo, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, adminContra, Toast.LENGTH_SHORT).show();

                    if(entrar() == true){
                        correo.setText("");
                        contra.setText("");
                        Intent intent = new Intent(MainActivity.this, Principal.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "CORREO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();
                        correo.setText("");
                        contra.setText("");
                    }



            }
        });



    }

    public boolean entrar(){

        DbAux dbAux = new DbAux(MainActivity.this);
        SQLiteDatabase db = dbAux.getReadableDatabase();
        Cursor cursorUsuario = null;
        TextInputEditText correo = findViewById(R.id.textoCorreo);
        EditText contrasena = findViewById(R.id.textoContra);
        String correoIntroducido = correo.getText().toString();
        String contrasenaIntroducido = contrasena.getText().toString();
        Boolean entra = false;

        cursorUsuario = db.rawQuery("SELECT * FROM usuarios WHERE correo='" + correoIntroducido + "' AND contrasena='" + contrasenaIntroducido + "'", null);

        if (cursorUsuario.moveToFirst() == false){
            entra = false;
        }else {
            entra = true;
        }

        cursorUsuario.close();
        return entra;
    }


}