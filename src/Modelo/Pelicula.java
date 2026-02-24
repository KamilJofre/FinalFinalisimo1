/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author kamil
 */
public class Pelicula {
    private int idPelicula;
    private int duracion;
    private String titulo;
    private String director;
    private String origen;
    private String genero;


    public Pelicula() {
    }

    public Pelicula(int idPelicula, String titulo, int duracion,
            String director, String origen, String genero) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.duracion = duracion;
        this.director = director;
        this.origen = origen;
        this.genero = genero;
    }
    
    

    public Pelicula(String titulo, int duracion,
            String director, String origen, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.director = director;
        this.origen = origen;
        this.genero = genero;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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
    
    @Override
    public String toString(){
        return titulo;
    }
}
