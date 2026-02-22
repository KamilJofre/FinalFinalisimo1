/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.time.LocalDate;
import java.time.LocalTime;

/*
idfuncion existente
sala id

idasiento correspondiente

 */
public class Funcion {
    private int idFuncion;
    private Pelicula pelicula;
    private Sala sala;
    private String idioma;
    private boolean es3D; 
    private boolean subtitulada;
    private LocalDate fechaFuncion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private double precio;
    


    public Funcion() {
    }

    public Funcion(int idFuncion, Pelicula pelicula, Sala sala, 
            String idioma, boolean es3D, boolean subtitulada, 
            LocalDate fechaFuncion, LocalTime horaInicio, LocalTime horaFin, double precio) {
        this.idFuncion = idFuncion;
        this.pelicula = pelicula;
        this.sala = sala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.fechaFuncion = fechaFuncion;
        this.horaInicio = horaInicio;
        this.horaFin= horaFin;
        this.precio = precio;
    }

    public Funcion(Pelicula pelicula, Sala sala, String idioma, boolean es3D, 
            boolean subtitulada, LocalDate fechaFuncion, LocalTime horaInicio, LocalTime horaFin, double precio) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.fechaFuncion = fechaFuncion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
    }
    
    

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isEs3D() {
        return es3D;
    }

    public void setEs3D(boolean es3D) {
        this.es3D = es3D;
    }

    public boolean isSubtitulada() {
        return subtitulada;
    }

    public void setSubtitulada(boolean subtitulada) {
        this.subtitulada = subtitulada;
    }

    public LocalDate getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(LocalDate fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString(){
        return "Funcion de las "+horaInicio +"hs" ;
    } 
}