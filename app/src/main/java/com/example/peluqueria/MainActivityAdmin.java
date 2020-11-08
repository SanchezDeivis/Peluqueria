package com.example.peluqueria;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.peluqueria.Adapters.AdaptadorPaginas;
import com.google.android.material.tabs.TabLayout;

public class MainActivityAdmin extends AppCompatActivity {
    TabLayout tabControlesSeleccion;
    ViewPager paginas;
    AdaptadorPaginas adaptadorPaginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

      /*  FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Button button = findViewById(R.id.buttonsalir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivityAdmin.this, LoginActivity.class));
                finish();
            }
        });*/

        tabControlesSeleccion = findViewById(R.id.tabControlesSelec);
        paginas = findViewById(R.id.paginas);
        tabControlesSeleccion.setupWithViewPager(paginas);

      //  tabControlesSeleccion.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(paginas));
        /*tabControlesSeleccion.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                paginas.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    adaptadorPaginas.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1) {
                    adaptadorPaginas.notifyDataSetChanged();
                }
                if (tab.getPosition() == 2) {
                    adaptadorPaginas.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
*/
        paginas.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabControlesSeleccion));
    }

}
