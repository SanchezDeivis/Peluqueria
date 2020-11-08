package com.example.peluqueria.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peluqueria.GridViewImagen;
import com.example.peluqueria.R;

import java.util.ArrayList;

/**
 * {@link BaseAdapter} personalizado para el gridview
 */
public class GridAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<GridViewImagen> vectorDatos;
    private TextView nombreGridView;
    private ImageView imageView;

    public GridAdapter(Context contexto, ArrayList<GridViewImagen> datos) {
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
            convertView = layout_inflater.inflate(R.layout.gridview_card, null);
        }
        nombreGridView = (TextView) convertView.findViewById(R.id.informacion);
        nombreGridView.setText(vectorDatos.get(i).getNameGridView());
        imageView=convertView.findViewById(R.id.imagenLogo);
        imageView.setImageResource(vectorDatos.get(i).getImagenGridView());

        return convertView;
    }
}

