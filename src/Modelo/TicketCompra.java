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
public class TicketCompra {
    private int idTicketCompra;
    private Comprador idComprador;
    private Asiento idAsiento;
    private Funcion idFuncion;
    private Date fechaCompra;
    private double monto;

    public TicketCompra() {
    }

    public TicketCompra(int idTicketCompra, Comprador idComprador, Asiento idAsiento, Funcion idFuncion, Date fechaCompra, double monto) {
        this.idTicketCompra = idTicketCompra;
        this.idComprador = idComprador;
        this.idAsiento = idAsiento;
        this.idFuncion = idFuncion;
        this.fechaCompra = fechaCompra;
        this.monto = monto;
    }

    public TicketCompra(Comprador idComprador, Asiento idAsiento, Funcion idFuncion, Date fechaCompra, double monto) {
        this.idComprador = idComprador;
        this.idAsiento = idAsiento;
        this.idFuncion = idFuncion;
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

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Funcion getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Funcion idFuncion) {
        this.idFuncion = idFuncion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    
}
