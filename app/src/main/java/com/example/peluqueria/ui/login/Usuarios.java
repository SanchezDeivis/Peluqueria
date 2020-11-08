package com.example.peluqueria.ui.login;

public class Usuarios {

    private String  nombre,celular,email,password;
    private int imagen;

    public Usuarios() {

    }

    public Usuarios(String nombre, String celular, String email, String password, int imagen) {
        this.nombre = nombre;
        this.celular = celular;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
