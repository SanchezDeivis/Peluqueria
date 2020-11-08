package com.example.peluqueria;

public class Cita {
    private String fecha, tipoServicio,hora;

    public Cita(){
    }

    public Cita(String fecha, String hora,String tipoServicio) {

        this.fecha = fecha;
        this.hora = hora;
        this.tipoServicio = tipoServicio;

    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
