/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;
import java.util.ArrayList;

public class TicketCompraData {

    private Connection conexion;

    public TicketCompraData(Connection con) {
        this.conexion = con;
    }

    //Insertar Ticket
    public void guardarTicket(TicketCompra t) {

        String sql = "INSERT INTO ticketcompra (idComprador, idAsiento, idFuncion, fechaCompra, monto) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, t.getIdComprador().getDni());
            ps.setInt(2, t.getIdAsiento().getIdAsiento());
            ps.setInt(3, t.getIdFuncion().getIdFuncion());
            ps.setDate(4, java.sql.Date.valueOf(t.getFechaCompra()));
            ps.setDouble(5, t.getMonto());

            ps.executeUpdate();

            // Obtener ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setIdTicketCompra(rs.getInt(1));
            }

            ps.close();
            System.out.println("Ticket guardado con éxito.");

        } catch (SQLException ex) {
            System.out.println("Error al guardar ticket: " + ex.getMessage());
        }
    }

    //Buscar ticket
    public TicketCompra buscarTicketCompra(int idTicketCompra) {

        TicketCompra t = null;
        String sql = "SELECT * FROM ticketcompra WHERE idTicketCompra=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idTicketCompra);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Objetos relacionados
                Comprador c = new Comprador();
                c.setDni(rs.getInt("idComprador"));

                Asiento a = new Asiento();
                a.setIdAsiento(rs.getInt("idAsiento"));

                Funcion f = new Funcion();
                f.setIdFuncion(rs.getInt("idFuncion"));

                t = new TicketCompra(
                        rs.getInt("idTicketCompra"),
                        c,
                        a,
                        f,
                        rs.getDate("fechaCompra").toLocalDate(),
                        rs.getDouble("monto")
                );
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar ticket: " + ex.getMessage());
        }

        return t;
    }

    //Listrar Tickets
    public ArrayList<TicketCompra> listarTicketCompra(int idComprador) {

        ArrayList<TicketCompra> lista = new ArrayList<>();

        String sql = "SELECT * FROM ticketcompra WHERE idComprador=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idComprador); // ✔ faltaba esto en tu versión

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TicketCompra t = new TicketCompra();
                t.setIdTicketCompra(rs.getInt("idTicketCompra"));

                // Comprador
                Comprador c = new Comprador();
                c.setDni(rs.getInt("idComprador"));
                t.setIdComprador(c);

                // Asiento
                Asiento a = new Asiento();
                a.setIdAsiento(rs.getInt("idAsiento"));
                t.setIdAsiento(a);

                // Función
                Funcion f = new Funcion();
                f.setIdFuncion(rs.getInt("idFuncion"));
                t.setIdFuncion(f);

                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setMonto(rs.getDouble("monto"));

                lista.add(t);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al listar tickets: " + ex.getMessage());
        }

        return lista;
    }

    //Actualizar tickets
    public void actualizarTicketCompra(TicketCompra t) {

        String sql = "UPDATE ticketcompra SET idComprador=?, idAsiento=?, idFuncion=?, fechaCompra=?, monto=? "
                   + "WHERE idTicketCompra=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, t.getIdComprador().getDni());
            ps.setInt(2, t.getIdAsiento().getIdAsiento());
            ps.setInt(3, t.getIdFuncion().getIdFuncion());
            ps.setDate(4, java.sql.Date.valueOf(t.getFechaCompra()));
            ps.setDouble(5, t.getMonto());
            ps.setInt(6, t.getIdTicketCompra());

            ps.executeUpdate();
            ps.close();

            System.out.println("Ticket actualizado correctamente.");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar ticket: " + ex.getMessage());
        }
    }

    //Borrar ticket
    public void borrarTicketCompra(int idTicketCompra) {

        String sql = "DELETE FROM ticketcompra WHERE idTicketCompra=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idTicketCompra);

            ps.executeUpdate();
            ps.close();

            System.out.println("Ticket eliminado correctamente.");

        } catch (SQLException ex) {
            System.out.println("Error al borrar ticket: " + ex.getMessage());
        }
    }
}