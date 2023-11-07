package com.example.soccerscout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerscout.adapter.ListaUsuariosAdapter;
import com.example.soccerscout.db.DbAux;
import com.example.soccerscout.usuarios.Usuario;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    RecyclerView listaUsuarios;
    ArrayList<Usuario> ArrayUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listaUsuarios = findViewById(R.id.listaUsuarios);

        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        ArrayUsuarios = new ArrayList<>();

        ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(mostrarContactos());

        listaUsuarios.setAdapter(adapter);


    }

    public ArrayList<Usuario> mostrarContactos(){

        DbAux dbAux = new DbAux(Principal.this);
        SQLiteDatabase db = dbAux.getWritableDatabase();

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario = null;
        Cursor cursorUsuario = null;

        cursorUsuario = db.rawQuery("SELECT * FROM usuarios", null);

        if (cursorUsuario.moveToFirst()){
            do {
                usuario = new Usuario();
                usuario.setId(cursorUsuario.getInt(0));
                usuario.setNombre(cursorUsuario.getString(1));
                usuario.setCorreo(cursorUsuario.getString(2));
                usuario.setContrasena(cursorUsuario.getString(3));

                listaUsuarios.add(usuario);

            }while(cursorUsuario.moveToNext());

        }

        cursorUsuario.close();

        return listaUsuarios;
    }

}