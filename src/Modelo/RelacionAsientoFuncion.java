/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kamil
 */
public class RelacionAsientoFuncion {
    private int idRelacion;
    private Funcion funcion;
    private Asiento asiento;
    private boolean ocupado;
    
    public RelacionAsientoFuncion(){
    }
    
    public RelacionAsientoFuncion(Funcion funcion, Asiento asiento, boolean ocupado){
        this.funcion=funcion;
        this.asiento=asiento;
        this.ocupado=ocupado;
    }
    
    public RelacionAsientoFuncion(int idRelacion, Funcion funcion, Asiento asiento, boolean ocupado){
        this.idRelacion=idRelacion;
        this.funcion=funcion;
        this.asiento=asiento;
        this.ocupado=ocupado;
    }

    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    @Override
    public String toString(){
        return asiento.getFila()+asiento.getNumero() ;
    }
}

