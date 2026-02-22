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
    private String lugar;
    private Sala sala;

    public Asiento() {}

    public Asiento(int idAsiento, String lugar, Sala sala) {
        this.idAsiento = idAsiento;
        this.lugar=lugar;
        this.sala = sala;
    }

    public Asiento( String lugar, Sala sala) {
        this.lugar=lugar;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
