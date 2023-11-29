
package com.example.soccerscout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Principal extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    FloatingActionButton btn_partidos;

    TextView placeholder;

    SearchView barraBusqueda;

    ConstraintLayout btn_crear;

    Boolean mostrarboton = true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.user);

        btn_partidos = findViewById(R.id.botonPartidos);

        btn_partidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, CrearPartido.class);
                startActivity(intent);
            }
        });
    }
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {

        //mostrarboton = true;

        if(item.getItemId()== R.id.user){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, firstFragment)
                    .commit();

            placeholder=(TextView) findViewById(R.id.titulo);
            placeholder.setText("PARTIDOS");

            barraBusqueda = (SearchView) findViewById(R.id.barrabusqueda);

            if(mostrarboton == false){
                btn_partidos.setVisibility(View.VISIBLE);
                barraBusqueda.setVisibility(View.VISIBLE);
                mostrarboton = true;
            }


            return true;
        } else if (item.getItemId()== R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, secondFragment)
                    .commit();

            placeholder=(TextView) findViewById(R.id.titulo);
            placeholder.setText("MI CUENTA");
            barraBusqueda = (SearchView) findViewById(R.id.barrabusqueda);


            if(mostrarboton == true){
                btn_partidos.setVisibility(View.INVISIBLE);
                barraBusqueda.setVisibility(View.INVISIBLE);
                mostrarboton = false;
            }

            return true;
        } else if (item.getItemId()== R.id.football) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .commit();

            placeholder=(TextView) findViewById(R.id.titulo);
            placeholder.setText("MIS PARTIDOS");

            barraBusqueda = (SearchView) findViewById(R.id.barrabusqueda);


            if(mostrarboton == true){
                btn_partidos.setVisibility(View.INVISIBLE);
                barraBusqueda.setVisibility(View.INVISIBLE);
                mostrarboton = false;
            }
            return true;
        }

        return false;
    }
}
