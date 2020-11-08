package com.example.peluqueria.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peluqueria.Adapters.PersonaAdacter;
import com.example.peluqueria.Persona;
import com.example.peluqueria.R;

import java.util.ArrayList;
import java.util.List;


public class Paginaadmin2 extends Fragment  {

    private List<Persona> personaList =new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_paginaadmin2, container, false);

        FillPersona();

        recyclerView = view.findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonaAdacter(personaList);
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void FillPersona(){

        personaList.add(new Persona(R.drawable.icon2, "Yesit Martinez","3104554343", "Estilista Profesional"," ",""  ));
        personaList.add(new Persona(R.drawable.icon1, "Jeancarlos morado","3104554343", "Barbero","11:50 a.m." ,""  ));
        personaList.add(new Persona(R.drawable.icon2, "Jesus Martinez","3104554343", "Profesión Peluquero","2:50 p.m." ,""  ));
        personaList.add(new Persona(R.drawable.icon1, "Osmara pereseras","3104554343 ", "Diseño de cejas en estructura facial","11:50 a.m." ,""  ));
        personaList.add(new Persona(R.drawable.icon2, "osnaider claren","3104554343", "Estilista","2:50 p.m."  ,"" ));
        personaList.add(new Persona(R.drawable.icon1, "Julia petrona caucil","3104554343", "COSMETOLOGÍA Y ESTÉTICA INTEGRAL","11:50 a.m.",""   ));

        personaList.add(new Persona(R.drawable.icon2, "josefina ariel","3104554343", "Manicurista","2:50 p.m.",""   ));
    }


}

