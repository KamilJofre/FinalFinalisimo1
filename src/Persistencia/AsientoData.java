/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;


public class AsientoData {

    private Connection conexion;

    public AsientoData(Connection conexion){
        this.conexion = conexion;
    }

    //Insertar
    public void insertar(Asiento a){
        String sql = "INSERT INTO asiento (fila, numero, estado, IdSala) "
                   + "VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, a.getFila());
            ps.setInt(2, a.getNumero());
            ps.setBoolean(3, a.isEstado());
            ps.setInt(4, a.getSala().getNroSala());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                a.setIdAsiento(rs.getInt(1));
            }

            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al guardar asiento: " + ex.getMessage());
        }
    }

    //Buscar
    public Asiento buscarAsiento(int idAsiento){
        Asiento a = null;
        String sql ="SELECT * FROM asiento WHERE idAsiento=?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAsiento);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Sala s = new Sala();
                s.setNroSala(rs.getInt("NroSala")); 
                
                a = new Asiento(
                    rs.getInt("idAsiento"),
                    rs.getString("fila"),
                    rs.getInt("numero"),
                    rs.getBoolean("estado"),
                    s
                );
            }

            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al buscar asiento: " + ex.getMessage());
        }
        return a;
    }

    

    //Actualizar
    public void actualizarAsiento(Asiento a){
        String sql ="UPDATE asiento SET fila=?, numero=?, estado=?, idSala=? "
                   + "WHERE idAsiento=?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, a.getFila());
            ps.setInt(2, a.getNumero());
            ps.setBoolean(3, a.isEstado());
            ps.setInt(4, a.getSala().getNroSala());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException ex){
            System.out.println("Error al actualizar asiento: " + ex.getMessage());
        }
    }

    //Borrar
    public void ocuparAsiento(int idAsiento){
        String sql = "UPDATE asiento SET estado = 0 WHERE idAsiento = ?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAsiento);
            ps.executeUpdate();
            ps.close();

        } catch(SQLException ex){
            System.out.println("Error al dar de baja asiento: " + ex.getMessage());
        }
    }
}