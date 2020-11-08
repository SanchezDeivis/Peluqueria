package com.example.peluqueria;

public class GridViewImagen {
    private String nameGridView;
    private int imagenGridView;

    public GridViewImagen(String nameGridView, int imagenGridView) {
        this.nameGridView = nameGridView;
        this.imagenGridView = imagenGridView;
    }

    public String getNameGridView() {
        return nameGridView;
    }

    public void setNameGridView(String nameGridView) {
        this.nameGridView = nameGridView;
    }

    public int getImagenGridView() {
        return imagenGridView;
    }

    public void setImagenGridView(int imagenGridView) {
        this.imagenGridView = imagenGridView;
    }
}
