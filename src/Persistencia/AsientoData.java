/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;
import java.util.*;

public class AsientoData {

    private Connection conexion;

    public AsientoData(Connection conexion){
        this.conexion = conexion;
    }

    //Insertar
    public void insertar(Asiento a){
        String sql = "INSERT INTO asiento (idFuncion, codLugar, fila, numero, estado) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, a.getIdFuncion().getIdFuncion());
            ps.setString(2, a.getCodLugar());
            ps.setString(3, a.getFila());
            ps.setInt(4, a.getNumero());
            ps.setBoolean(5, a.isEstado());

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
                Funcion f = new Funcion();
                f.setIdFuncion(rs.getInt("idFuncion"));

                a = new Asiento(
                    rs.getInt("idAsiento"),
                    f,
                    rs.getString("codLugar"),
                    rs.getString("fila"),
                    rs.getInt("numero"),
                    rs.getBoolean("estado")
                );
            }

            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al buscar asiento: " + ex.getMessage());
        }
        return a;
    }

    //Listar
    public List<Asiento> listarAsientos(int idFuncion){
        List<Asiento> lista = new ArrayList<>();
        String sql ="SELECT * FROM asiento WHERE idFuncion=?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idFuncion);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Funcion f = new Funcion();
                f.setIdFuncion(rs.getInt("idFuncion"));

                Asiento a = new Asiento(
                    rs.getInt("idAsiento"),
                    f,
                    rs.getString("codLugar"),
                    rs.getString("fila"),
                    rs.getInt("numero"),
                    rs.getBoolean("estado")
                );

                lista.add(a);
            }

            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al listar asientos: " + ex.getMessage());
        }

        return lista;
    }

    //Actualizar
    public void actualizarAsiento(Asiento a){
        String sql ="UPDATE asiento SET idFuncion=?, codLugar=?, fila=?, numero=?, estado=? "
                   + "WHERE idAsiento=?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, a.getIdFuncion().getIdFuncion());
            ps.setString(2, a.getCodLugar());
            ps.setString(3, a.getFila());
            ps.setInt(4, a.getNumero());
            ps.setBoolean(5, a.isEstado());
            ps.setInt(6, a.getIdAsiento());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException ex){
            System.out.println("Error al actualizar asiento: " + ex.getMessage());
        }
    }

    //Borrar
    public void darDeBaja(int idAsiento){
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