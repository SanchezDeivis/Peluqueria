package com.example.peluqueria.Adapters;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peluqueria.MainActivityPersona;
import com.example.peluqueria.Persona;
import com.example.peluqueria.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonaAdacter extends RecyclerView.Adapter<PersonaAdacter.PersonaViewHolder> {

    private List<Persona> items;


    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        public CardView PersonaCardView;
        public CircleImageView ImagenV;
        public TextView Nombre;
        public TextView Celular;
        public TextView profesion;

        public PersonaViewHolder(View v) {
            super(v);
            PersonaCardView = v.findViewById(R.id.personaCard);
            ImagenV = v.findViewById(R.id.image);
            Nombre = v.findViewById(R.id.nombre);
            /*Celular = v.findViewById(R.id.telefono);*/
            profesion = v.findViewById(R.id.profesionCard);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public PersonaAdacter(List<Persona> items) {
        this.items = items;
    }

    public List<Persona> getItems() {
        return this.items;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_card, viewGroup, false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder viewHolder, final int i) {
        viewHolder.ImagenV.setImageResource(items.get(i).getImagen());
        viewHolder.Nombre.setText(items.get(i).getNombre());
        /*viewHolder.Celular.setText(items.get(i).getCelular());*/
        viewHolder.profesion.setText(items.get(i).getProfesion());


        viewHolder.PersonaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("curImagen", items.get(i).getImagen());
                bundle.putString("curNombre", items.get(i).getNombre());
                bundle.putString("curCelular", items.get(i).getCelular());
                bundle.putString("curProfesion", items.get(i).getProfesion());

                Intent icoIntent = new Intent(view.getContext(), MainActivityPersona.class);
                icoIntent.putExtras(bundle);
                view.getContext().startActivity(icoIntent);
            }
        });

    }


}
