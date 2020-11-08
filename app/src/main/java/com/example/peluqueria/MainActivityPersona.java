package com.example.peluqueria;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.peluqueria.fragments.Fragment1;
import com.example.peluqueria.fragments.Pagina2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityPersona extends AppCompatActivity {

    public ImageView ImagenV;
    private String Nombre;
    public TextView Profesion, telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_persona);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();




            }
        });

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                    finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }*/

        ImagenV = findViewById(R.id.image);
        Profesion = findViewById(R.id.profesionCard);
        telefono = findViewById(R.id.telefono);

        ImagenV.setImageResource(getIntent().getExtras().getInt("curImagen"));
        Nombre = (getIntent().getExtras().getString("curNombre"));
        telefono.setText(getIntent().getExtras().getString("curCelular"));
        Profesion.setText(getIntent().getExtras().getString("curProfesion"));


        this.setTitle(Nombre);
    }

}
