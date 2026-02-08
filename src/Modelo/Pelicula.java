/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.util.Date;


/**
 *
 * @author kamil
 */
public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String director;
    private String origen;
    private String genero;

    private boolean enCartelera;

    public Pelicula() {
    }
    
    public Pelicula(String titulo, String director, 
            String origen, String genero, boolean enCartelera) {
        this.titulo = titulo;
        this.director = director;
        this.origen = origen;
        this.genero = genero;
        this.enCartelera = enCartelera;
    }

    public Pelicula(int idPelicula, String titulo, String director, 
            String origen, String genero, boolean enCartelera) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;

        this.origen = origen;
        this.genero = genero;

        this.enCartelera = enCartelera;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public boolean isEnCartelera() {
        return enCartelera;
    }

    public void setEnCartelera(boolean enCartelera) {
        this.enCartelera = enCartelera;
    }
}
