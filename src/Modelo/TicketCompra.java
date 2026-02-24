/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author kamil
 */
public class TicketCompra {
    private int idTicketCompra;
    private Funcion funcion;
    private RelacionAsientoFuncion relacion;
    private Comprador idComprador;
    private LocalDate fechaCompra;
    private double monto;

    public TicketCompra() {
    }

    public TicketCompra(int idTicketCompra, Funcion funcion, RelacionAsientoFuncion relacion, 
            Comprador idComprador, LocalDate fechaCompra, double monto) {
        this.idTicketCompra = idTicketCompra;
        this.funcion = funcion;
        this.relacion = relacion;
        this.idComprador = idComprador;
        this.fechaCompra = fechaCompra;
        this.monto = monto;
    }
    
    public TicketCompra(Funcion funcion, RelacionAsientoFuncion relacion, 
            Comprador idComprador, LocalDate fechaCompra, double monto) {
        this.funcion = funcion;
        this.relacion = relacion;
        this.idComprador = idComprador;
        this.fechaCompra = fechaCompra;
        this.monto = monto;
    }
    
    public int getIdTicketCompra() {
        return idTicketCompra;
    }

    public void setIdTicketCompra(int idTicketCompra) {
        this.idTicketCompra = idTicketCompra;
    }

    public Comprador getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Comprador idComprador) {
        this.idComprador = idComprador;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public RelacionAsientoFuncion getRelacion() {
        return relacion;
    }

    public void setRelacion(RelacionAsientoFuncion relacion) {
        this.relacion = relacion;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
}
