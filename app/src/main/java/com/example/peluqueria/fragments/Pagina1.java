package com.example.peluqueria.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.peluqueria.Adapters.GridAdapter;
import com.example.peluqueria.GridViewImagen;
import com.example.peluqueria.R;

import java.util.ArrayList;
import java.util.List;


public class Pagina1 extends Fragment {


    private GridAdapter adaptador;
    private GridView gridView;

    private List<GridViewImagen> gridViewImagenList = new ArrayList<>();

    private GridLayoutManager adapter;


    public Pagina1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagina1, container, false);

        addcarviewiten();

        gridView = (GridView) view.findViewById(R.id.gridview);

        adaptador = new GridAdapter(view.getContext(), (ArrayList<GridViewImagen>) gridViewImagenList);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(vista.getContext());

                final View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                final Spinner mspinner = mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.peluqueriaList));
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mspinner.setAdapter(adapter1);
                FragmentManager fragmentManager = getParentFragmentManager();
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!mspinner.getSelectedItem().toString().equalsIgnoreCase("Selecciona una op...")) {

                            if (mspinner.getSelectedItem().toString().equals("Pedir a Domicilio")) {
                                Toast.makeText(mView.getContext(), mspinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                                fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment2()).commit();
                            } else if (mspinner.getSelectedItem().toString().equals("Agendar un el cervicio")) {
                                Toast.makeText(mView.getContext(), mspinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                                fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment1()).commit();
                            }
                        }
                    }
                });
                mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }

        });


        return view;
    }

    private void addcarviewiten() {
        gridViewImagenList.add(new GridViewImagen("Cepillado Caballero", R.drawable.cepilladocaballero));
        gridViewImagenList.add(new GridViewImagen("Catalogo Cortes", R.drawable.corteespecial));
        gridViewImagenList.add(new GridViewImagen("Figuras", R.drawable.figuras));
        gridViewImagenList.add(new GridViewImagen("Depilado de cejas", R.drawable.depilacejas));
        gridViewImagenList.add(new GridViewImagen("Herramientas de corte", R.drawable.herramientacorte));
        gridViewImagenList.add(new GridViewImagen("Corte caballeros", R.drawable.cortecaballero));
        gridViewImagenList.add(new GridViewImagen("Maquillaje Damas", R.drawable.maquillajedamda));
        gridViewImagenList.add(new GridViewImagen("Maniquiu y pediquiu", R.drawable.pediquiu));
        gridViewImagenList.add(new GridViewImagen("Cortes", R.drawable.cortemoderno));
        gridViewImagenList.add(new GridViewImagen("Cortes con maquina", R.drawable.corteamaquina));
        gridViewImagenList.add(new GridViewImagen("Herramientas", R.drawable.herrramientas));
        gridViewImagenList.add(new GridViewImagen("Estilos de cortes", R.drawable.estilos2020));
        gridViewImagenList.add(new GridViewImagen("Lo mejor para ti", R.drawable.depilacejas));
        gridViewImagenList.add(new GridViewImagen("Corte a tijeras", R.drawable.tijeras));
        gridViewImagenList.add(new GridViewImagen("Tratamiento cejas", R.drawable.tratamientocejas));
        gridViewImagenList.add(new GridViewImagen("Que esperas para cambiar", R.drawable.estilos));

    }

}
