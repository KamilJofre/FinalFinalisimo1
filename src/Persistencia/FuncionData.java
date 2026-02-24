/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class FuncionData {

    private Connection conexion;

    public FuncionData(Connection con) {
        this.conexion = con; 
    }

    //insertar funcion
    public void guardarFuncion(Funcion f) {
        String sql = "INSERT INTO funcion (idPelicula , nroSala, idioma, es3D, subtitulada, fechaFuncion, horaInicio, horaFin, precio) "
                   + "VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, f.getPelicula().getIdPelicula());
            ps.setInt(2, f.getSala().getNroSala());
            ps.setString(3, f.getIdioma());
            ps.setBoolean(4, f.isEs3D());
            ps.setBoolean(5, f.isSubtitulada());
            ps.setDate(6, java.sql.Date.valueOf(f.getFechaFuncion()));
            ps.setTime(7, java.sql.Time.valueOf(f.getHoraInicio()));
            ps.setTime(8, java.sql.Time.valueOf(f.getHoraFin()));
            ps.setDouble(9, f.getPrecio());

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
    
    //chequear posibilidad para horario de funcion
    public boolean solapamiento(int NroSala, LocalDate fechaNueva,  LocalTime nueavaInicio, LocalTime nuevaFin){
        String sql = """
                     SELECT *
                     FROM funcion 
                     WHERE NroSala=? 
                     AND fechaFuncion=?
                     AND ? < horaFin
                     AND ? > horaInicio
                     """;
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, NroSala);
            ps.setObject(2,fechaNueva);
            ps.setObject(3, nueavaInicio);
            ps.setObject(4, nuevaFin);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1)>0;
            }
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error: ya existe una funcion durante la hora seleccionada" + ex.getMessage());
        }
        return false;
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
                    rs.getDate("fechaFuncion").toLocalDate(),
                    rs.getTime("horaInicio").toLocalTime(),  
                    rs.getTime("horaFin").toLocalTime(),
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
        String sql = """             
                    SELECT 
                        f.idFuncion,
                        f.fechaFuncion,
                        f.horaInicio,
                        f.horaFin,
                        f.idioma,
                        f.es3D,
                        f.subtitulada,
                        f.precio,
                        s.nroSala AS nroSala,
                        p.idPelicula AS idPelicula,
                        p.titulo AS titulo_pelicula
                    FROM funcion f
                    JOIN sala s ON f.nroSala = s.nroSala
                    JOIN pelicula p ON f.idPelicula = p.idPelicula
                """;

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcion f = new Funcion();

                f.setIdFuncion(rs.getInt("idFuncion"));

                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                p.setTitulo(rs.getString("titulo_pelicula"));
                f.setPelicula(p);

                Sala s = new Sala();
                s.setNroSala(rs.getInt("NroSala"));
                f.setSala(s);

                f.setIdioma(rs.getString("idioma"));
                f.setEs3D(rs.getBoolean("es3D"));
                f.setSubtitulada(rs.getBoolean("subtitulada"));
                f.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                f.setHoraInicio(rs.getTime("horaInicio").toLocalTime());    
                f.setHoraFin(rs.getTime("horaFin").toLocalTime());        
                f.setPrecio(rs.getDouble("precio"));            

                lista.add(f);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al listar funciones: " + ex.getMessage());
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
                f.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                f.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                f.setPrecio(rs.getInt("precio"));
                
                lista.add(f);
            }   
        } catch (SQLException ex) {
            System.out.println("Error al listar funciones: " + ex.getMessage());
        }
        return lista;
    }
    
    //funcionesDePelicula
    public ArrayList<Funcion> funcionesDePelicula(int idPelicula) {
        ArrayList<Funcion> lista = new ArrayList<>();
        String sql = """             
                    SELECT f.*, p.titulo AS titulo_pelicula, s.NroSala
                    FROM funcion f
                    JOIN pelicula p ON f.idPelicula = p.idPelicula
                    JOIN sala s ON f.NroSala = s.NroSala
                    WHERE f.idPelicula=?
                    ORDER BY f.fechaFuncion, f.horaInicio
                """;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1,idPelicula);
            rs = ps.executeQuery();

            while (rs.next()) {
                Funcion f = new Funcion();

                f.setIdFuncion(rs.getInt("idFuncion"));

                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                p.setTitulo(rs.getString("titulo_pelicula"));
                f.setPelicula(p);

                Sala s = new Sala();
                s.setNroSala(rs.getInt("NroSala"));
                f.setSala(s);

                f.setIdioma(rs.getString("idioma"));
                f.setEs3D(rs.getBoolean("es3D"));
                f.setSubtitulada(rs.getBoolean("subtitulada"));
                f.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                f.setHoraInicio(rs.getTime("horaInicio").toLocalTime());    
                f.setHoraFin(rs.getTime("horaFin").toLocalTime());        
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
        String sql = "UPDATE funcion SET sala=?, pelicula=?, idioma=?, es3D=?, subtitulada=?, fechaFuncion=?,"
                +" horaInicio=?,  horaFin=?,precio=? "
                   + "WHERE idFuncion=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, f.getSala().getNroSala());
            ps.setInt(2, f.getPelicula().getIdPelicula());
            ps.setString(3, f.getIdioma());
            ps.setBoolean(4, f.isEs3D());
            ps.setBoolean(5, f.isSubtitulada());
            ps.setDate(6, java.sql.Date.valueOf(f.getFechaFuncion()));
            ps.setTime(7, java.sql.Time.valueOf(f.getHoraInicio()));
            ps.setTime(8,java.sql.Time.valueOf(f.getHoraFin()));
            ps.setDouble(9, f.getPrecio());

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