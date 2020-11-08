package com.example.peluqueria;

public class Domicilios {
    private String fecha, hora,tipoServicio,latitud,longitud, usuario;

    public Domicilios() {
    }

    public Domicilios(String fecha, String hora, String tipoServicio, String latitud, String longitud, String usuario) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoServicio = tipoServicio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
