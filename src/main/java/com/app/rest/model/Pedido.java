package com.app.rest.model;

public class Pedido {

    private String nombre;
    private int id;
    private boolean vegano;


    public Pedido(int id, String nombre, boolean vegano) {
        this.nombre=nombre;
        this.id=id;
        this.vegano=vegano;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public boolean isVegano() {
        return vegano;
    }
    @Override
    public String toString() {
        return "Pedido{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", vegano=" + vegano +
                '}';
    }

}
