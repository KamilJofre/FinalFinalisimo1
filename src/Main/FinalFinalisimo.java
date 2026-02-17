package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import Persistencia.*;
import Modelo.*;
import java.sql.*;
import java.util.Date;


/**
 *
 * @author kamil
 */
public class FinalFinalisimo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        
        
        //Conexion a base de datos
        Connection con = Conexion.getConexion();
        
        

        //ComrpadorData
        CompradorData cd = new CompradorData(con);

        // 1) Crear comprador
        Comprador c = new Comprador(1232456, "Juan asda", new java.util.Date(), "abc123", "Debito");
        Comprador k = new Comprador(45562799, "Kamil Jofré", new java.util.Date(2004-04-21),"abcbc","Efectivo" );

        // 2) Insertar
        cd.insertar(c);
        cd.insertar(k);
        

        // 3) Buscar
        Comprador resultado = cd.buscarComprador(45562799);
        System.out.println("Buscado: " + resultado.getNombre());

        // 4) Actualizar
        c.setNombre("Juan Carlos Pérez");
        cd.actualizarComprador(c);

        // 5) Listar todos
        for (Comprador x : cd.listarCompradores()) {
            System.out.println("Comprador: " + x.getNombre());
        }

        
        
        //SalatData
        SalaData salaData = new SalaData(con);

        System.out.println("\nPrueba de SalaData");

        // 1) Crear una sala
        Sala nuevaSala = new Sala(1, true, 120, true);
        salaData.insertar(nuevaSala);

        // 2) Buscar sala
        Sala salaBuscada = salaData.buscarSala(1);
        System.out.println("Sala encontrada: " + salaBuscada.getNroSala());

        // 3) Actualizar sala
        salaBuscada.setCapacidad(150);
        salaData.actualizarSala(salaBuscada);

        // 4) Listar salas
        for (Sala s : salaData.listarSalas()) {
            System.out.println("Sala listada: " + s.getNroSala());
        }


        
        
        
        //PeliculaData
        PeliculaData pd = new PeliculaData(con);

        // 1) Crear pelicula
        Pelicula KFP = new Pelicula("Kunf Fu Panda -4", "Jack Black", "USA", "Animacion");
        pd.guardarPelicula(KFP);

        // 2) Buscar
        Pelicula encontrada = pd.buscarPorIdPelicula(KFP.getIdPelicula());
        System.out.println("Película encontrada: " + encontrada.getTitulo());

        // 3) Actualizar
        encontrada.setTitulo("Kunf Fu Panda -4");
        pd.actualizarPelicula(encontrada);

        // 4) Listar
        for (Pelicula peli : pd.listarPeliculas()) {
            System.out.println("Película: " + peli.getIdPelicula() + " - " + peli.getTitulo());
        }
//
//
        //FuncionData
        FuncionData fd = new FuncionData(con);

        Funcion m = new Funcion(
                KFP,                  
                nuevaSala,          
                "Español",          
                false,              
                true,               
                new java.util.Date(2026-01-01),         
                new Date(),                 
                6000                
        );

        fd.guardarFuncion(m);
        System.out.println("Función guardada ID: " + m.getIdFuncion());

        Funcion funEncontrada = fd.buscarFuncion(m.getIdFuncion());
        System.out.println("Idioma función encontrada: " + funEncontrada.getIdioma());

//
//
        //AsientoData
        AsientoData ad = new AsientoData(con);

        // 1) Insertar asiento
        Asiento a = new Asiento(m, "A1", "A", 1, false);
        ad.insertar(a);

        System.out.println("Asiento insertado con ID: " + a.getIdAsiento());

        // 2) Buscar asiento
        Asiento encontradoAsiento = ad.buscarAsiento(a.getIdAsiento());
        System.out.println("Asiento hallado: " + encontradoAsiento.getCodLugar());

        // 3) Listar asientos por función
        for (Asiento x : ad.listarAsientos(m.getIdFuncion())) {
            System.out.println("Asiento listado: " + x.getCodLugar());
        }
//
//
//        
//
        //TicketCompraDaata (Final)
        TicketCompraData tcd = new TicketCompraData(con);

        // 1) Crear ticket
        TicketCompra ticket = new TicketCompra(
                k,          // comprador
                a,          // asiento
                m,          // funcion
                new Date(), // fechaCompra
                m.getPrecio()
        );

        tcd.guardarTicket(ticket);

        System.out.println("Ticket guardado ID: " + ticket.getIdTicketCompra());

        // 2) Buscar ticket
        TicketCompra tBuscado = tcd.buscarTicketCompra(ticket.getIdTicketCompra());
        System.out.println("Ticket encontrado monto: " + tBuscado.getMonto());

        // 3) Listar tickets de comprador
        for (TicketCompra t : tcd.listarTicketCompra(c.getDni())) {
            System.out.println("Ticket listado ID: " + t.getIdTicketCompra());
        }
//        
//   
//       

        System.out.println("\n Fin Pruebas"); 
        
        */

    }
}
