/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;
import java.util.*;

public class FuncionData {

    private Connection conexion;

    public FuncionData(Connection con) {
        this.conexion = con; // ✔ Correcto
    }

    //insertar funcion
    public void guardarFuncion(Funcion f) {
        String sql = "INSERT INTO funcion (NroSala, idPelicula, idioma, es3D, subtitulada, fechaFuncion, horaInicio, precio) "
                   + "VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, f.getIdSala().getNroSala());
            ps.setInt(2, f.getIdPelicula().getIdPelicula());
            ps.setString(3, f.getIdioma());
            ps.setBoolean(4, f.isEs3D());
            ps.setBoolean(5, f.isSubtitulada());
            ps.setDate(6, new java.sql.Date(f.getFechaFuncion().getTime()));
            ps.setTimestamp(7, new Timestamp(f.getHoraInicio().getTime()));  
            ps.setDouble(8, f.getPrecio()); 

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                f.setIdFuncion(rs.getInt(1));
            }

            ps.close();
            System.out.println("Función guardada correctamente.");

        } catch (SQLException ex) {
            System.out.println("Error al guardar función: " + ex.getMessage());
        }
    }

    //Buscar funcion
    public Funcion buscarFuncion(int idFuncion) {
        Funcion f = null;
        String sql = "SELECT * FROM funcion WHERE idFuncion=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idFuncion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));

                Sala s = new Sala();
                s.setNroSala(rs.getInt("NroSala"));

                f = new Funcion(
                    rs.getInt("idFuncion"),
                    p,
                    s,
                    rs.getString("idioma"),
                    rs.getBoolean("es3D"),
                    rs.getBoolean("subtitulada"),
                    rs.getDate("fechaFuncion"),
                    rs.getTimestamp("horaInicio"),  
                    rs.getDouble("precio")        
                );
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println(" Error al buscar función: " + ex.getMessage());
        }

        return f;
    }

    //Listar
    public ArrayList<Funcion> listarFunciones() {
        ArrayList<Funcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcion";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcion f = new Funcion();

                f.setIdFuncion(rs.getInt("idFuncion"));

                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                f.setIdPelicula(p);

                Sala s = new Sala();
                s.setNroSala(rs.getInt("NroSala"));
                f.setIdSala(s);

                f.setIdioma(rs.getString("idioma"));
                f.setEs3D(rs.getBoolean("es3D"));
                f.setSubtitulada(rs.getBoolean("subtitulada"));
                f.setFechaFuncion(rs.getDate("fechaFuncion"));
                f.setHoraInicio(rs.getTimestamp("horaInicio"));        
                f.setPrecio(rs.getDouble("precio"));            

                lista.add(f);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al listar funciones: " + ex.getMessage());
        }

        return lista;
    }

    //Actualizar
    public void actualizarFuncion(Funcion f) {
        String sql = "UPDATE funcion SET NroSala=?, idPelicula=?, idioma=?, es3D=?, subtitulada=?, fechaFuncion=?, horaInicio=?, precio=? "
                   + "WHERE idFuncion=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, f.getIdSala().getNroSala());
            ps.setInt(2, f.getIdPelicula().getIdPelicula());
            ps.setString(3, f.getIdioma());
            ps.setBoolean(4, f.isEs3D());
            ps.setBoolean(5, f.isSubtitulada());
            ps.setDate(6, new java.sql.Date(f.getFechaFuncion().getTime()));
            ps.setTimestamp(7, new Timestamp(f.getHoraInicio().getTime()));
            ps.setDouble(9, f.getPrecio());
            ps.setInt(10, f.getIdFuncion());

            ps.executeUpdate();
            ps.close();

            System.out.println("Función actualizada correctamente.");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar función: " + ex.getMessage());
        }
    }

    //Borrar
    public void borrarFuncion(int idFuncion) {
        String sql = "DELETE FROM funcion WHERE idFuncion=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idFuncion);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al borrar función: " + ex.getMessage());
        }
    }
}