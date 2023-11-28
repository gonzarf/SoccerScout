package com.example.soccerscout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearPartido  extends AppCompatActivity {

    Button btn_CrearPartido;
    TextInputEditText nombre, fecha, hora, numero;
    FirebaseFirestore mfirestore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearpartido);

        mfirestore = FirebaseFirestore.getInstance();

        btn_CrearPartido = findViewById(R.id.btnCrearPartido);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        hora = findViewById(R.id.hora);
        numero = findViewById(R.id.numero);

        btn_CrearPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombrePartido = nombre.getText().toString().trim();
                String fechaPartido = fecha.getText().toString().trim();
                String horaPartido = hora.getText().toString().trim();
                String numeroPartido = numero.getText().toString().trim();

                if (nombrePartido.isEmpty() && fechaPartido.isEmpty() && horaPartido.isEmpty() && numeroPartido.isEmpty()){
                    Toast.makeText(CrearPartido.this, "INTRODUZCA TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                }else{
                    addPartido(nombrePartido, fechaPartido, horaPartido, numeroPartido);
                }
            }
        });
    }

    private void addPartido(String nombrePartido, String fechaPartido, String horaPartido, String numeroPartido) {

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombrePartido);
        map.put("fecha", fechaPartido);
        map.put("hora", horaPartido);
        map.put("numero", numeroPartido);

        mfirestore.collection("partido").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
        });
    }


}
