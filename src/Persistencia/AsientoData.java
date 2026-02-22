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
        String sql = "INSERT INTO asiento (lugar, IdSala) VALUES (?, ?)";
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, a.getLugar());
            ps.setInt(2, a.getSala().getNroSala());

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
    
    public void cargarAsientosSala(int idSala, int capacidad){
    //CAPACIDAD = (COLUMNAS X FILAS)+ RESTO para una sala ideal (lo mas simetrica posible)
    //entonces la sala es cuadrada, entonces se puede obtener un numero mediante
    //sqrt(capacidad), siendo esta cantidad posible tanto filas como para culmnas,
    //solo pq si se elige que sean las filas y las ecuaciones restantes salen con eso
    //COLUMNAS = CAPACIDAD/FILAS
    //RESTO    = CAPACIDAD%FILAS
    /*EJEMPLO
    Cantidad =20
    filas = sqrt(20) = 4
    columnas = 20/4=5
    resto=0
    entonces se forman  4 filas y 5 columnas
        A,A,A,A,A
        A,A,A,A,A
        A,A,A,A,A
        A,A,A,A,A

    si hubieran sido 21
    cantidad =21
    filas sqrt(21)=5
    columnas=4
    resto=1
       A,A,A,A,A
        A,A,A,A
        A,A,A,A
        A,A,A,A
    */

        int cantidadAsientos = capacidad;
        int filas= (int) Math.sqrt(cantidadAsientos);
        int columnas = cantidadAsientos/filas;
        int resto    = cantidadAsientos%filas;
        
        String sql = "INSERT INTO asiento (lugar,NroSala) VALUES(?,?)";
        PreparedStatement ps = null;
        
        try{Connection con =Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            for(int i=0; i<filas; i++){
            char letraFila= (char) ('A'+i);
            int asientosEnFila = columnas;
                if (i<resto){  
                    asientosEnFila++;
                }
                for(int j=1; j<=asientosEnFila; j++){
                    String lugar = letraFila + String.valueOf(j);
                    ps.setString(1, lugar);
                    ps.setInt(2, idSala);
                    ps.executeUpdate();
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if (ps!=null) ps.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
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
                    rs.getString("lugar"),
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
        String sql ="UPDATE asiento SET lugar=?, idSala=? "
                   + "WHERE idAsiento=?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, a.getLugar());
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