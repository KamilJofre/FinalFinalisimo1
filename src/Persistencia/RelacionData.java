/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import Modelo.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RelacionData {
    private Connection conexion;

    public RelacionData(Connection con) {
        this.conexion = con;
    }
    
    //FUNCION -> SALA
    //ASIENTOS -> SALA
    public ArrayList<Asiento> listarAsientosDeFuncion(int idFuncion){
        ArrayList<Asiento> lista = new ArrayList<>();
        String sql="""
                   SELECT *
                   FROM asiento
                   WHERE NroSala = (
                        SELECT NroSala
                        FROM funcion
                        WHERE idFuncion=?
                   )ORDER BY fila AND numero
                   """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, idFuncion);
        
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asiento a = new Asiento();
                    a.setIdAsiento(rs.getInt("idAsiento"));
                    a.setFila(rs.getString("fila"));
                    a.setNumero(rs.getInt("numero"));
                    
                    Sala s = new Sala();
                    s.setNroSala(rs.getInt("NroSala"));
                    a.setSala(s);
                    
                    lista.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Eroor al listar los asientos de la funcion" + ex.getMessage());
        } 
        return lista;
    }
    
    public void estadoAsiento(int idFuncion, int idAsiento){
        //chequea el estado de un lugar
    }
    
    public void ocuparAsiento(){
        //ocupa lugar mediante seleccion del cliente
    }
    
}
