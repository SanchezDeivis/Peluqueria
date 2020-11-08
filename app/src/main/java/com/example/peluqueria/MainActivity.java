package com.example.peluqueria;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.peluqueria.fragments.Fragment1;
import com.example.peluqueria.fragments.Fragment2;
import com.example.peluqueria.fragments.Fragment3;
import com.example.peluqueria.fragments.Fragment4;
import com.example.peluqueria.fragments.ImportFragment;
import com.example.peluqueria.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String mireferences = "nightModePrefers";
    public static final String KEY_ISNIGHTMODE = "isnightMode";
    EditText put;

    private Switch theme;
    private Button theme1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      /* if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darTheme);
        } else setTheme(R.style.AppTheme);*/
        /*SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences (this);
        boolean status = (shared.getBoolean ("dark_mode", false));
        if(status) AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
        else  AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sharedPreferences = getSharedPreferences(mireferences, Context.MODE_PRIVATE);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new ImportFragment()).commit();
        theme1 = findViewById(R.id.btncolor);

        theme = findViewById(R.id.Switch_Naght_Mode);
        /* checkNightModeActivated();*/
       /* theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    recreate();
                }
            }

        } );*/



        /*loadSettings();
        events();*/
        /*if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            theme.setChecked(true);
        }
        theme.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });*/
    }

    /*public void T(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        saveNightModeState(true);
        recreate();
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISNIGHTMODE, nightMode);
        editor.apply();
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(KEY_ISNIGHTMODE, false)) {
            //theme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            *//* theme.setChecked(false);*//*
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }* hgygygyuuhuhihiijio/


/*    private void loadSettings()
    {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences (this);
        boolean dark = (shared.getBoolean ("dark_mode", false));
        theme.setChecked (dark);
    }

    private void events()
    {
        theme.setOnCheckedChangeListener ((buttonView, isChecked) ->
        {
            SharedPreferences mDefaultPreferences = PreferenceManager.getDefaultSharedPreferences (this);
            if (isChecked)
            {
                mDefaultPreferences.edit ().putBoolean ("dark_mode", true).apply ();
                AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
            }

            else
            {
                mDefaultPreferences.edit ().putBoolean ("dark_mode", false).apply ();
                AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_serrar) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new ImportFragment()).commit();
        } else if (id == R.id.nav_agenda) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment1()).commit();
        } else if (id == R.id.nav_domicilios) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment2()).commit();
        } else if (id == R.id.nav_tools) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment3()).commit();
        } else if (id == R.id.nav_contacto) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment4()).commit();
        } else if (id == R.id.nav_serrar) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment5()).commit();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//metodo complemento para ayudar acambiar el tema a night
    public void restartApp() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}
