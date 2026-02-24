/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import Persistencia.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class AsientoData {

    private Connection conexion;
    private RelacionData RelacionData;

    public AsientoData(Connection conexion){
        this.conexion = conexion;
    }

    //Insertar
    public void insertar(Asiento a){
        String sql = "INSERT INTO asiento (fila, numero, IdSala) VALUES (?,?, ?)";
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, a.getFila());
            ps.setInt(2, a.getNumero());
            ps.setInt(3, a.getSala().getNroSala());

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
    
    public void cargarAsientosCreandoSala(int idSala, int capacidad){
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
        
        String sql = "INSERT INTO asiento (fila, numero,NroSala) VALUES(?,?,?)";
        PreparedStatement ps = null;
        
        try{Connection con =Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            for(int i=0; i<filas; i++){
            char filaChar= (char) ('A'+i);
            int asientosEnFila = columnas;
                if (i<resto){  
                    asientosEnFila++;
                }
                for(int j=1; j<=asientosEnFila; j++){
                    ps.setString(1, String.valueOf(filaChar));
                    ps.setInt(2,j);
                    ps.setInt(3, idSala);
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
                    rs.getString("fila"),
                    rs.getInt("numero"),
                    s
                );
            }

            ps.close();
        } catch(SQLException ex){
            System.out.println("Error al buscar asiento: " + ex.getMessage());
        }
        return a;
    }
    
    public ArrayList<Asiento> listarAsientosSala(int NroSala) {
        ArrayList<Asiento> lista = new ArrayList<>(); 
        String sql="""
                   SELECT *
                   FROM asiento
                   WHERE NroSala=?
                   """;
        //Imprimir ordenado
        
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, NroSala);
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
        } catch(SQLException ex){
            System.out.println("Error al mostrar asientos"+ex.getMessage());
        }
        
        Map<String, List<Asiento>> asientosPorFila = new TreeMap<>();
        
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

    

    //Borrar
    public void BorrarAsiento(Sala sala,String fila, int numero){
        String sql = "DELETE asiento WHERE NroSala=? AND fila = ? AND numero=?";

        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, sala.getNroSala());
            ps.setString(2, fila);
            ps.setInt(3, numero);
            ps.executeUpdate();
            ps.close();

        } catch(SQLException ex){
            System.out.println("Error al dar de baja asiento: " + ex.getMessage());
        }
    }
}