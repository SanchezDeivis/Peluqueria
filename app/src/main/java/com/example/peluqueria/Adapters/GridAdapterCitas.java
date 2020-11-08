package com.example.peluqueria.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peluqueria.Cita;
import com.example.peluqueria.R;

import java.util.ArrayList;

/**
 * {@link BaseAdapter} personalizado para el gridview
 */
public class GridAdapterCitas extends BaseAdapter {

    private Context contexto;
    private ArrayList<Cita> vectorDatos;
    private TextView fecha, hora, tipoServicio;


    public GridAdapterCitas(Context contexto, ArrayList<Cita> datos) {
        //Se asignan los valores a los atributos de la clase AdaptadorGridView.
        this.contexto = contexto;
        this.vectorDatos = datos;
    }

    @Override
    public int getCount() {
        return vectorDatos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return vectorDatos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layout_inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
            convertView = layout_inflater.inflate(R.layout.gridview_card_cita, null);
        }

        fecha = convertView.findViewById(R.id.fechaCita);
        fecha.setText(vectorDatos.get(i).getFecha());
        hora = convertView.findViewById(R.id.horaCita);
        hora.setText(vectorDatos.get(i).getHora());
        tipoServicio = convertView.findViewById(R.id.servicio);
        tipoServicio.setText(vectorDatos.get(i).getTipoServicio());

        return convertView;
    }
}

