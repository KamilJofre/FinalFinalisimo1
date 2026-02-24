/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import Modelo.*;
import java.sql.*;
import java.util.*;

public class RelacionData {
    private Connection conexion;

    public RelacionData(Connection con) {
        this.conexion = con;
    }
    
    public void borrarRAF(int idAsiento){
        String sql="DELETE relacionasientodata WHERE idAsiento=?";
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, idAsiento);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            System.out.println("Error al borrar la RAF"+ex.getMessage());
        }
    }
    
    //FUNCION -> SALA
    //ASIENTOS -> SALA
    public ArrayList<RelacionAsientoFuncion> listarAsientosDeFuncion(int idFuncion){
        ArrayList<RelacionAsientoFuncion> lista = new ArrayList<>();
        String sql="""
                   SELECT raf.idRelacion,
                          raf.idFuncion,
                          raf.idAsiento,
                          raf.ocupado,
                          a.fila,
                          a.numero
                   FROM relacionasientofuncion raf
                   JOIN asiento a ON raf.idAsiento = a.idAsiento
                   WHERE raf.idFuncion = ?
                   ORDER BY a.fila, a.numero;
                   """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, idFuncion);
        
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RelacionAsientoFuncion raf = new RelacionAsientoFuncion();
                    raf.setIdRelacion(rs.getInt("idRelacion"));
                    
                    Funcion f = new Funcion();
                    f.setIdFuncion(rs.getInt("idFuncion"));
                    raf.setFuncion(f);
                    
                    Asiento a = new Asiento();
                    a.setIdAsiento(rs.getInt("idAsiento"));
                    a.setFila(rs.getString("fila"));
                    a.setNumero(rs.getInt("numero"));
                    
                    raf.setAsiento(a);
                    
                    lista.add(raf);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los asientos de la funcion" + ex.getMessage());
        } 
        return lista;
    }
    
    public ArrayList<Asiento> imprimirAsientosOrdenado(int idFuncion){
        ArrayList<Asiento> lista = new ArrayList<>();
        String sql="""
                   SELECT *
                   FROM asiento
                   WHERE NroSala=(
                        SELECT NroSala
                        FROM funcion
                        WHERE idFuncion=?
                   )
                   """;
        Map<String, List<Asiento>> asientosPorFila = new TreeMap<>();
        
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, idFuncion);
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                Asiento a = new Asiento();
                a.setIdAsiento(rs.getInt("idAsiento"));
                a.setFila(rs.getString("fila"));
                a.setNumero(rs.getInt("numero"));
                
                Sala s = new Sala();
                    s.getNroSala();
                a.setSala(s);
                
                lista.add(a);
                }
            }
            
        } catch (SQLException ex) {
            System.getLogger(RelacionData.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
       for(Asiento a: lista){
            String fila = a.getFila().toUpperCase();
            asientosPorFila.putIfAbsent(fila, new ArrayList<>());
            asientosPorFila.get(fila).add(a);
        }
        
        for(String fila : asientosPorFila.keySet()){
            System.out.print(fila + ": ");
            List<Asiento> asientos = asientosPorFila.get(fila);
            asientos.sort(Comparator.comparingInt(Asiento::getNumero));
            for(Asiento a: asientos){
               System.out.print(a.getNumero() + " ");
            }
            System.out.println();
        }
        return lista;
    } 
        
    
    public void crearRelacionAsientoFuncion(int idFuncion){
        PreparedStatement ps =null;
        String sql="""
                   INSERT INTO relacionasientofuncion (idFuncion, idAsiento, ocupado)
                   SELECT f.idFuncion, a.idAsiento, null
                   FROM funcion f
                   JOIN asiento a ON f.NroSala=a.NroSala
                   WHERE f.idFuncion=?
                   """;
        
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idFuncion);
            int filasInsertadas = ps.executeUpdate();
            System.out.println("Relaciones creadas: " +filasInsertadas);
            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al guardar la RAF "+ex.getMessage());
        }
    }
    
    public boolean estadoAsiento(int idFuncion, int idAsiento){

    String sql = """
                 SELECT ocupado 
                 FROM relacionasientofuncion
                 WHERE idFuncion = ? AND idAsiento = ?
                 """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idFuncion);
            ps.setInt(2, idAsiento);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("ocupado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar estado del asiento " + ex.getMessage());
        }

        return false; // si no existe lo toma como libre
    }
    
    public boolean ocuparAsiento(int idFuncion, int idAsiento){

    // Primero verificamos estado
    if (estadoAsiento(idFuncion, idAsiento)) {
        System.out.println("El asiento ya está ocupado.");
        return false;
        }
        String sql = """
                     UPDATE relacionasientofuncion
                     SET ocupado = true
                     WHERE idFuncion = ? AND idAsiento = ?
                     """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idFuncion);
            ps.setInt(2, idAsiento);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Asiento ocupado correctamente.");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al ocupar asiento " + ex.getMessage());
        }
        return false;
    }
    
    public void borrarGrupoRelaciones(int idFuncion){
        String sql="DELETE FROM relacionasientofuncion WHERE idFuncion=?";
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, idFuncion);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al borrar la RAF pertinente" + ex.getMessage());
        }
    }
    
}
