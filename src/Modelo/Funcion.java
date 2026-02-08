/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.*;
import java.util.Date;

/**
 *
 * @author kamil
 */
public class Funcion {
    private int idFuncion;
    private Pelicula idPelicula;
    private Sala idSala;
    private String idioma;
    private boolean es3D; 
    private boolean subtitulada;
    private Date fechaFuncion;
    private Date horaInicio;
    private double precio;
    
    



    public Funcion() {
    }

    public Funcion(int idFuncion, Pelicula idPelicula, Sala idSala, String idioma, boolean es3D, boolean subtitulada, Date fechaFuncion, Date horaInicio, double precio) {
        this.idFuncion = idFuncion;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.fechaFuncion = fechaFuncion;
        this.horaInicio = horaInicio;
        this.precio = precio;
    }

    public Funcion(Pelicula idPelicula, Sala idSala, String idioma, boolean es3D, boolean subtitulada, Date fechaFuncion, Date horaInicio,double precio) {
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.fechaFuncion = fechaFuncion;
        this.horaInicio = horaInicio;
        this.precio = precio;
    }
    
    

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
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

    public Date getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(Date fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}