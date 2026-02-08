/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Comprador;
import java.sql.*;
import java.util.*;
/**
 *
 * @author kamil
 */
public class CompradorData {
    
    //CONECTAR
    private Connection conexion;
    
    
    public CompradorData(Connection conexion){
       this.conexion = conexion;
    }
    
    //INSERTAR
    public void insertar(Comprador c){
        String sql = "INSERT INTO comprador (dni, nombre, fechaNac, password, medioPago) VALUES (?, ?, ?, ?, ?)";
        try{ 
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setDate(3, new java.sql.Date(c.getFechaNac().getTime()));
            ps.setString(4, c.getPassword());
            ps.setString(5, c.getMedioPago());
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Comprador agregado correctamente (dni=" + c.getDni() + ").");
            } else {
                System.out.println("No se encontró comprador para actualizar (dni=" + c.getDni() + ").");
            }
            
           
            
            ps.close();
                
        } catch (SQLException ex) {
            System.out.println("Error: " +ex.getMessage());
        } 
    }
    
    //BUSCAR X ID
    public Comprador buscarComprador(int dni) {
        Comprador c = null;
        String sql ="SELECT dni, nombre, fechaNac, password, medioPago FROM comprador WHERE dni = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, dni);
        
            ResultSet rs= ps.executeQuery();
            if (rs.next()){ 
                c = new Comprador(
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getDate("fechaNac"),
                        rs.getString("password"),
                        rs.getString("medioPago")
                        );
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error: al buscar comprador: " + ex.getMessage());
        }
        return c;
    }
   
    //LISTAR TODOS              buscamos compradores de una funcion
    public List<Comprador> listarCompradores(){
        List<Comprador> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, fechaNac, password, medioPago FROM comprador";
       
        try{
            PreparedStatement ps= conexion.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
                Comprador c = new Comprador(
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getDate("fechaNac"),
                        rs.getString("password"),
                        rs.getString("medioPago")
                );
                lista.add(c);
            }
            ps.close();
        }   catch(SQLException ex){
            System.out.println("Error: al listar compradores "+ex.getMessage());
        }
        return lista;  
        
        
    }
    
    /**
     * Lista compradores que compraron en una función (idFuncion).
     * Usa JOIN con ticketcompra.
     */
    public List<Comprador> listarCompradoresPorFuncion(int idFuncion) {
        List<Comprador> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT c.dni, c.nombre, c.fechaNac, c.password, c.medioPago " +
                     "FROM comprador c " +
                     "JOIN ticketcompra t ON c.dni = t.idComprador " +
                     "WHERE t.idFuncion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idFuncion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comprador c = new Comprador(
                            rs.getInt("dni"),
                            rs.getString("nombre"),
                            rs.getDate("fechaNac"),
                            rs.getString("password"),
                            rs.getString("medioPago")
                    );
                    lista.add(c);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar compradores por función: " + ex.getMessage());
        }
        return lista;
    }
    
    
    
    
    //ACTUALIZAR
    public void actualizarComprador(Comprador c){
        String sql = "UPDATE comprador SET nombre = ?, fechaNac = ?, password = ?, medioPago = ? WHERE dni = ?";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setDate(2, new java.sql.Date(c.getFechaNac().getTime()));
            
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getMedioPago());
            ps.setInt(5, c.getDni());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Comprador actualizado correctamente (dni=" + c.getDni() + ").");
            } else {
                System.out.println("No se encontró comprador para actualizar (dni=" + c.getDni() + ").");
            }
            
            
        } catch(SQLException ex){
            System.out.println("Error: " +ex.getMessage());
        }
    }
    
    // BAJA LOGICA
    public void darDeBaja(int dni) {
        String sql = "DELETE FROM comprador WHERE dni = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, dni);
            ps.executeUpdate();
            ps.close();
            
        int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Comprador eliminado (dni=" + dni + ").");
            } else {
                System.out.println("No se encontró comprador para eliminar (dni=" + dni + ").");
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar comprador: " + ex.getMessage());
        }
    }
}
