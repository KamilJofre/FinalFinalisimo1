/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author kamil
 */
public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/gp3_proyectofinal_cinemacentro_sql?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // cambiar si corresponde

    private static Connection conexion = null;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASS);
                JOptionPane.showMessageDialog(null, "Se conecto a la base de datos");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null,"Driver JDBC no encontrado: " + e.getMessage());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error conectando a la DB: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            } finally {
                conexion = null;
            }
        }
    }
}
