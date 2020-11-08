package com.example.peluqueria;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.peluqueria.fragments.Fragment1;
import com.example.peluqueria.fragments.Fragment2;
import com.example.peluqueria.fragments.Paginaadmin1;
import com.example.peluqueria.fragments.Paginaadmin2;
import com.example.peluqueria.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {
    private Paginaadmin1 Paginaadmin1;
    private Paginaadmin2 Paginaadmin2;
    private Fragment1 Fragment1;
    private Fragment2 Fragment2;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_persona:
                    setFragment(Paginaadmin2);
                    setTitle(R.string.title_personas);
                    return true;
                case R.id.navigation_servicio:
                    setFragment(Paginaadmin1);
                    setTitle(R.string.title_servicio);
                    return true;
                case R.id.navigation_Cita:
                    setFragment(Fragment1);
                    setTitle(R.string.title_citas);
                    return true;
                case R.id.navigation_domicilio:
                    setFragment(Fragment2);
                    setTitle(R.string.title_domicilio);
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_Fragment_Contenido, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        Paginaadmin1 = new Paginaadmin1();
        Paginaadmin2 = new Paginaadmin2();
        Fragment1 = new Fragment1();
        Fragment2 = new Fragment2();
        //Obteniendo la instancia
        ActionBar actionBar = getActionBar();


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.id_Fragment_Contenido, Paginaadmin2).commit();
//Escondiendo la Action Bar
      //  actionBar.hide();
    }
    //Metodos para el uso del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Obteniendo la instancia
        ActionBar actionBar = getActionBar();

        switch (item.getItemId()) {

            case R.id.search:
                //metodoSearch()

                return true;
            case R.id.edit:
                //metodoEdit()
                Toast.makeText(this, "Mi primer menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                //metodoDelete()
                //Mostrando de nuevo la Action Bar
                actionBar.show();
                return true;
            case R.id.action_serrar:
                //metodoSettings()
                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                mAuth.signOut();
                startActivity(new Intent(Admin.this, LoginActivity.class));
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




}
