/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author kamil
 */
public class Asiento {
    private int idAsiento;
    private String fila;
    private int numero;
    private boolean estado;
    private Sala sala;

    public Asiento() {}

    public Asiento(int idAsiento, String fila, int numero, boolean estado, Sala sala) {
        this.idAsiento = idAsiento;
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
        this.sala = sala;
    }

    public Asiento( String fila, int numero, boolean estado, Sala sala) {
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
        this.sala= sala;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
