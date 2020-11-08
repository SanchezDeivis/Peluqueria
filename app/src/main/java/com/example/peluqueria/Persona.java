package com.example.peluqueria;

public class Persona {
    private String  nombre,celular,profesion,user,password;
    private int imagen;

    public Persona(int imagen,String nombre, String celular, String profesion, String user, String password) {
        this.imagen=imagen;
        this.nombre = nombre;
        this.celular = celular;
        this.profesion = profesion;
        this.user = user;
        this.password=password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String ficho) {
        this.profesion = ficho;
    }


}
