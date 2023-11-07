package com.example.soccerscout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soccerscout.db.DbAux;

public class Registro extends AppCompatActivity {

    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagistro);

        btnCrear = findViewById(R.id.botonRegistro);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombreEditText = findViewById(R.id.nombre);
                EditText correoEditText = findViewById(R.id.correo);
                EditText contraEditText = findViewById(R.id.contrasena);

                String nombreU = nombreEditText.getText().toString();
                String correoU = correoEditText.getText().toString();
                String contraU = contraEditText.getText().toString();

                DbAux dbAux = new DbAux(Registro.this);
                SQLiteDatabase db = dbAux.getWritableDatabase();

                if (db != null && !nombreU.isEmpty() && !correoU.isEmpty() && !contraU.isEmpty()){

                    ContentValues values = new ContentValues();
                    values.put("nombre", nombreU);
                    values.put("correo", correoU);
                    values.put("contrasena", contraU);
                    /*Toast.makeText(Registro.this, values.get("nombre").toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registro.this, values.get("correo").toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registro.this, values.get("contrasena").toString(), Toast.LENGTH_SHORT).show();*/
                    long res = db.insert("usuarios", null, values);
                    if(res >=0){
                        Toast.makeText(Registro.this, "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        nombreEditText.setText("");
                        correoEditText.setText("");
                        contraEditText.setText("");

                        Intent intent = new Intent(Registro.this, Principal.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(Registro.this, "ERROR AL INSERTAR DATOS", Toast.LENGTH_SHORT).show();
                    }
                    db.close();

                }else {
                    Toast.makeText(Registro.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}