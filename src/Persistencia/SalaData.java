/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author kamil
 */
public class SalaData {
    //conectar
   
    private Connection conexion;
    public SalaData(Connection conexion){
        this.conexion =  conexion;
    }
    
    //INSERTAR inserta datos
    public void insertar(Sala s){
        String sql = "INSERT INTO sala (NroSala, apta3D, capacidad, estado) VALUES (?, ?, ?, ?)";
        try{ 
            //metodos para llevarle los datos a la base
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            //ahi lo que pasa es que es un objetio del tipo Funcion
            //entonces tenes q llamar al objeto mediante getFuncion()
            //y a ese objeto pedirle la id
            ps.setInt(1, s.getNroSala());
            ps.setBoolean(2, s.isApta3D());
            ps.setInt(3, s.getCapacidad());
            ps.setBoolean(4, s.isEstado());
            
            
            // ahi le manda eso a la base
            ps.executeUpdate();
            ps.close();
            
            
            System.out.println("Sala insertada correctamente.");
                
        } catch (SQLException ex) {
            System.out.println("Error al guardar sala:" +ex.getMessage());
        } 
    }
    
    //BUSCAR X ID
    public Sala buscarSala(int NroSala) {
        Sala s = null;
        String sql ="SELECT * FROM sala WHERE NroSala=?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, NroSala);
        
            ResultSet rs= ps.executeQuery();
            if (rs.next()){ 
                s = new Sala(
                        rs.getInt("NroSala"),
                        rs.getBoolean("apta3D"),
                        rs.getInt("capacidad"),
                        rs.getBoolean("estado")
                        );
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar sala" + ex.getMessage());
        }
        return s;
    }
    
    //LISTAR TODOS
    public List<Sala> listarSalas(){
        List<Sala> lista = new ArrayList<>();
        String sql ="SELECT * FROM sala ";
        
        try{
            PreparedStatement ps= conexion.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
                //mas de lo mismo
                
                Sala s= new Sala(
                        rs.getInt("NroSala"),
                        rs.getBoolean("apta3D"),
                        rs.getInt("capacidad"),
                        rs.getBoolean("estado")
                    );
                lista.add(s);
            }
            ps.close();
        }   catch(SQLException ex){
            System.out.println("Erros: al listar salas: "+ex.getMessage());
        }
        return lista;
    }
    
    public ArrayList<Funcion> listarFuncionesDeSala(int NroSala){
        
        ArrayList<Funcion> lista = new ArrayList<>();
        
    String sql = "SELECT f.* FROM funcion f WHERE f.NroSala = ?";    


        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, NroSala);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Funcion f = new Funcion();
                f.setIdFuncion(rs.getInt("idFuncion"));
                
                // Película
                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                f.setPelicula(p);
                
                // Sala
                Sala s = new Sala();
                s.setNroSala(rs.getInt("NroSala"));
                f.setSala(s);
                
                f.setIdioma(rs.getString("idioma"));
                f.setEs3D(rs.getBoolean("es3D"));
                f.setSubtitulada(rs.getBoolean("subtitulada"));
                f.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                f.setPrecio(rs.getInt("precio"));
                
                lista.add(f);
            }   
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al listar funciones: " + ex.getMessage());
        }
        return lista;
    }
    
    //ACTUALIZAR
    public void actualizarSala(Sala s){
        String sql ="UPDATE sala SET apta3D=?, capacidad=?, estado=? WHERE NroSala=?";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            //devuelta
            ps.setBoolean(1, s.isApta3D());
            ps.setInt(2, s.getCapacidad());
            ps.setBoolean(3, s.isEstado());
            ps.setInt(4, s.getNroSala());
            
            ps.executeUpdate();
            ps.close();
            
             System.out.println("Sala actualizada.");
        } catch(SQLException ex){
            System.out.println("Error: al actualizar sala" +ex.getMessage());
        }
    }
    
    // BAJA LOGICA
    public void darDeBaja(int NroSala) {
        String sql = "UPDATE sala SET estado = 0 WHERE NroSala = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, NroSala);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al dar de baja sala: " + ex.getMessage());
        }
    }
}
