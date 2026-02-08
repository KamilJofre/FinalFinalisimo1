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
public class PeliculaData {

    private Connection conexion;

    public PeliculaData(Connection con){
        this.conexion = con;
    }

    //Insertar pelicula
    public void guardarPelicula(Pelicula p){
        String sql ="INSERT INTO pelicula (titulo, director, origen, genero, enCartelera) "
                   + "VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setString(3, p.getOrigen());
            ps.setString(4, p.getGenero());
            ps.setBoolean(5, p.isEnCartelera());

            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) p.setIdPelicula(rs.getInt(1));
            System.out.println("Pelicula ingresada correctamente");
            
            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al guardar película: "+ex.getMessage());
        }
    }

    //Buscar
    public Pelicula buscarPelicula(int idPelicula){
        Pelicula p=null;
        String sql="SELECT * FROM pelicula WHERE idPelicula=?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                p = new Pelicula(
                    rs.getInt("idPelicula"),
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getString("origen"),
                    rs.getString("genero"),
                    rs.getBoolean("enCartelera")
                );
            }
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("rror al buscar película: " + ex.getMessage());
        }
        return p;
    }

    //Listar
    public List<Pelicula> listarPeliculas(){
        List<Pelicula> lista = new ArrayList<>();
        String sql ="SELECT * FROM pelicula";
        
        try{
            PreparedStatement ps= conexion.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
                Pelicula p = new Pelicula(
                        rs.getInt("idPelicula"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getString("origen"),
                        rs.getString("genero"),
                        rs.getBoolean("enCartelera")
                );
                lista.add(p);
            }
            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al listar películas: "+ex.getMessage());
        }
        return lista;
    }

    //Actualizar
    public void actualizarPelicula(Pelicula p) {
        String sql = "UPDATE pelicula SET titulo=?, director=?, origen=?, genero=?, enCartelera=? "
                   + "WHERE idPelicula=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setString(3, p.getOrigen());
            ps.setString(4, p.getGenero());
            ps.setBoolean(5, p.isEnCartelera());
            ps.setInt(6, p.getIdPelicula());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar película: " + ex.getMessage());
        }
    }

    //Borrar
    public void borrarPelicula(int idPelicula) {
        String sql = "DELETE FROM pelicula WHERE idPelicula=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPelicula);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al borrar película: " + ex.getMessage());
        }
    }
}