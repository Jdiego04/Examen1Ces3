package com.edu.poli.ces3.models;

import java.util.List;

public class Libro {
    private String codigo;
    private String titulo;
    private int anyo;
    private List<Autor> autores;
    private String editorial;
    private double precio;

    private String autor;
    // Constructor
    public Libro(String codigo, String titulo, int anyo, List<Autor> autores, String editorial, double precio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anyo = anyo;
        this.autores = autores;
        this.editorial = editorial;
        this.precio = precio;
    }

    public Libro(String titulo, int anyo, String autor) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.autor = autor;

    }

    // Getters y setters

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getAnyo() {
        return anyo;
    }
    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
    public List<Autor> getAutores() {
        return autores;
    }
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

