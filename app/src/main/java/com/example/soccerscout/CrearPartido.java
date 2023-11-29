package com.example.soccerscout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccerscout.adapter.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class CrearPartido  extends AppCompatActivity {
    // Declaración de variables miembro
    Button btn_CrearPartido;
    TextInputEditText nombre, fecha, hora, numero;


    FirebaseFirestore mfirestore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearpartido);

        // Inicialización de Firebase
        FirebaseApp.initializeApp(this);

        // Inicialización de FirebaseFirestore
        mfirestore = FirebaseFirestore.getInstance();

        // Asignación de referencias a elementos de la interfaz de usuario
        btn_CrearPartido = findViewById(R.id.btnCrearPartido);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        hora = findViewById(R.id.hora);
        numero = findViewById(R.id.numero);

        // Configuración del listener de clic para el botón "Crear Partido"
        btn_CrearPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Obtención de los valores ingresados por el usuario
                String nombrePartido = nombre.getText().toString().trim();
                String fechaPartido = fecha.getText().toString().trim();
                String horaPartido = hora.getText().toString().trim();
                String numeroPartido = numero.getText().toString().trim();

                // Verificación de que todos los campos estén completos
                if (nombrePartido.isEmpty() && fechaPartido.isEmpty() && horaPartido.isEmpty() && numeroPartido.isEmpty()){
                    Toast.makeText(CrearPartido.this, "INTRODUZCA TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                }else{
                    // Llamada al método para agregar el partido a Firebase Firestore
                    addPartido(nombrePartido, fechaPartido, horaPartido, numeroPartido);
                }
            }
        });
    }
    // Método para agregar un partido a Firebase Firestore
    private void addPartido(String nombrePartido, String fechaPartido, String horaPartido, String numeroPartido) {

        Map<String, User> map = new HashMap<>();
        User u1 = new User();
        u1.nombre = nombrePartido;
        u1.fecha = fechaPartido;
        u1.hora = horaPartido;
        u1.numero = numeroPartido;

        // Agregando la información del partido al mapa
        map.put(nombrePartido, u1);

        // Accediendo a la colección "SoccerScout" y al documento "Partido" en Firestore
        mfirestore.collection("SoccerScout").document("Partido")
                // Estableciendo el mapa como datos del documento
                .set(map)
                // Manejando el éxito de la operación
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CrearPartido.this, "TODO OK", Toast.LENGTH_SHORT).show();
                        Log.d("DEBUG", "TODO OK");
                    }
                })
                // Manejando el fallo de la operación
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CrearPartido.this, "TODO MAL", Toast.LENGTH_SHORT).show();
                        Log.d("ERROR", e.getMessage());
                    }
                });


        /*mfirestore.collection("partido").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(CrearPartido.this, "PARTIDO AÑADIDO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CrearPartido.this, "ERROR AL INGRESAR LOS DATOS", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


}
