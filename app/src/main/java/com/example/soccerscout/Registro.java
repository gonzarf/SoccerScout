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

        // Asignar el botón de registro en el layout a la variable btnCrear
        btnCrear = findViewById(R.id.botonRegistro);
        // Configurar un listener de clic para el botón de registro
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener referencias a los EditText en el layout
                EditText nombreEditText = findViewById(R.id.nombre);
                EditText correoEditText = findViewById(R.id.correo);
                EditText contraEditText = findViewById(R.id.contrasena);

                // Obtener los valores ingresados por el usuario en los EditText
                String nombreU = nombreEditText.getText().toString();
                String correoU = correoEditText.getText().toString();
                String contraU = contraEditText.getText().toString();

                // Crear una instancia de la clase DbAux para trabajar con la base de datos SQLite
                DbAux dbAux = new DbAux(Registro.this);
                SQLiteDatabase db = dbAux.getWritableDatabase();

                // Verificar que la base de datos no sea nula y que los campos no estén vacíos
                if (db != null && !nombreU.isEmpty() && !correoU.isEmpty() && !contraU.isEmpty()){

                    // Crear un objeto ContentValues para almacenar los valores a insertar en la base de datos
                    ContentValues values = new ContentValues();
                    values.put("nombre", nombreU);
                    values.put("correo", correoU);
                    values.put("contrasena", contraU);
                    /*Toast.makeText(Registro.this, values.get("nombre").toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registro.this, values.get("correo").toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registro.this, values.get("contrasena").toString(), Toast.LENGTH_SHORT).show();*/

                    // Insertar los valores en la tabla 'usuarios' de la base de datos
                    long res = db.insert("usuarios", null, values);

                    // Verificar el resultado de la inserción
                    if(res >=0){
                        
                        // Mostrar un mensaje de éxito y limpiar los EditText
                        Toast.makeText(Registro.this, "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        nombreEditText.setText("");
                        correoEditText.setText("");
                        contraEditText.setText("");

                        Intent intent = new Intent(Registro.this, Principal.class);
                        startActivity(intent);

                        // Mostrar un mensaje de error en caso de fallo en la inserción
                    }else{
                        Toast.makeText(Registro.this, "ERROR AL INSERTAR DATOS", Toast.LENGTH_SHORT).show();
                    }
                    // Cerrar la conexión a la base de datos
                    db.close();

                    // Mostrar un mensaje de error si la base de datos es nula o los campos están vacíos
                }else {
                    Toast.makeText(Registro.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}