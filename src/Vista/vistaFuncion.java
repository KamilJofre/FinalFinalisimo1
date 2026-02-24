/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.*; 
import Persistencia.*;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author kamil
 */
public class vistaFuncion extends javax.swing.JFrame {
    private JSpinner horaInicio;
    private SalaData SalaData;
    private PeliculaData PeliculaData;
    private FuncionData FuncionData;
    Connection con = Conexion.getConexion();
    FuncionData pd = new FuncionData(con);

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(vistaFuncion.class.getName());

    /**
     * Creates new form vistaFuncion
     */
    public vistaFuncion() {
        initComponents();
        FuncionData = new FuncionData(Conexion.getConexion());
        PeliculaData = new PeliculaData(Conexion.getConexion());
        SalaData = new SalaData(Conexion.getConexion());
        cargarTablaSalas();
        cargarTablaFunciones();
        cargarTablaPeliculas();
        placeHolder(jTextNumeroSala, "NUMERO SALA");
        placeHolder(jTextFuncionPelicula, "PELICULA");
        placeHolder(jTextFuncionIdioma, "IDIOMA");
        placeHolder(jTextFuncionPrecio, "PRECIO");
        placeHolder(jTextFieldFuncionEliminar,"Id Funcion");
        
        SpinnerDateModel modeloHora= new SpinnerDateModel();
        jSpinnerHoraInicio.setModel(modeloHora);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpinnerHoraInicio,"HH:mm");
        jSpinnerHoraInicio.setEditor(editor);
    }
    
    public void placeHolder(JTextField txt, String texto){
        Color colorPlaceholder = new Color(0,0,0,120);
        Color colorTexto = Color.BLACK;
        
        txt.setText(texto);
        txt.setForeground(colorPlaceholder);
        
        txt.addFocusListener(new FocusAdapter(){
            @Override
                public void focusGained(FocusEvent e){
                if(txt.getText().equals(texto)){
                    txt.setText("");
                    txt.setForeground(colorTexto);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e){
                if(txt.getText().isEmpty()){
                    txt.setText(texto);
                    txt.setForeground(colorPlaceholder);
                }
            }   
        });
    }
    private void cargarTablaSalas() {
    DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"Nro Sala", "Capacidad", "Apta 3D", "Estado"}, 0
        );
        jTableSalas.setModel(modelo);
        
        for (Sala s : SalaData.listarSalas()) {
            String es3d;
            if(s.isApta3D()){
                es3d="Si.";
            } else{
                es3d="No.";
            }
            modelo.addRow(new Object[]{
                s.getNroSala(),
                s.getCapacidad(),
                es3d,
                s.isEstado()
            });
        }
    }
    private void cargarTablaFunciones() {
    DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"Id Funcion","Numero Sala", "Pelicula", "Idioma", "Fecha","Hora Inicio","Hora Fin","Precio"}, 0
        );
        jTableListaFunciones.setModel(modelo);

        for (Funcion f : FuncionData.listarFunciones()) {
//            if(f.isEs3D()){
//                String titulo3d= f.getPelicula().getTitulo()+"(3D)";
//            }
            modelo.addRow(new Object[]{
                f.getIdFuncion(),
                f.getSala().getNroSala(),
                f.getPelicula().getTitulo() + (f.isEs3D() ? " (3D)" : ""),
                f.getIdioma(),
                f.getFechaFuncion(),
                f.getHoraInicio(),
                f.getHoraFin(),
                f.getPrecio()
            });
        }
    }
    void cargarTablaPeliculas() {
    DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"Id Película","Titulo", "Duracion","Director", "Origen", "Genero"}, 0
        );
        jTableListaPeliculas.setModel(modelo);
        for (Pelicula p : PeliculaData.listarPeliculas()) {
            int minutos=p.getDuracion();
            int horas = p.getDuracion()/60;
            int restante = p.getDuracion()%60;
            
            modelo.addRow(new Object[]{
                p.getIdPelicula(),
                p.getTitulo(),
                horas+"h"+restante+"m",
                p.getDirector(),
                p.getOrigen(),
                p.getGenero()
            });
        }
    }
    private void inicializarSpinnerHora() {
        SpinnerDateModel modeloHora =
            new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        horaInicio = new JSpinner(modeloHora);
        JSpinner.DateEditor editor =
            new JSpinner.DateEditor(horaInicio, "HH:mm");
        horaInicio.setEditor(editor);
        jSpinnerHoraInicio.add(horaInicio); // panel donde lo querés mostrar
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTextFuncionIdioma = new javax.swing.JTextField();
        jTextNumeroSala = new javax.swing.JTextField();
        jTextFuncionPelicula = new javax.swing.JTextField();
        jTextFuncionPrecio = new javax.swing.JTextField();
        jCheckBoxFuncion3D = new javax.swing.JCheckBox();
        jCheckBoxFuncionSubt = new javax.swing.JCheckBox();
        jDateFechaFuncion = new com.toedter.calendar.JDateChooser();
        jSpinnerHoraInicio = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonVolver = new javax.swing.JButton();
        jButtonEnviarFuncion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaFunciones = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableSalas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableListaPeliculas = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldFuncionEliminar = new javax.swing.JTextField();
        jButtonEliminarFuncion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(100, 149, 237));

        jTextFuncionIdioma.setText("IDIOMA");
        jTextFuncionIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFuncionIdiomaActionPerformed(evt);
            }
        });

        jTextNumeroSala.setText("NUMERO SALA");
        jTextNumeroSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNumeroSalaActionPerformed(evt);
            }
        });

        jTextFuncionPelicula.setText("PELICULA");
        jTextFuncionPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFuncionPeliculaActionPerformed(evt);
            }
        });

        jTextFuncionPrecio.setText("PRECIO");
        jTextFuncionPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFuncionPrecioActionPerformed(evt);
            }
        });

        jCheckBoxFuncion3D.setText("ES 3D");
        jCheckBoxFuncion3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFuncion3DActionPerformed(evt);
            }
        });

        jCheckBoxFuncionSubt.setText("SUBTITULADA");
        jCheckBoxFuncionSubt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFuncionSubtActionPerformed(evt);
            }
        });

        jDateFechaFuncion.setToolTipText("FECHA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFuncionPelicula)
                    .addComponent(jTextFuncionIdioma)
                    .addComponent(jDateFechaFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinnerHoraInicio)
                    .addComponent(jTextFuncionPrecio)
                    .addComponent(jTextNumeroSala)
                    .addComponent(jCheckBoxFuncionSubt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxFuncion3D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextNumeroSala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFuncionPelicula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFuncionIdioma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFechaFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerHoraInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFuncionPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxFuncionSubt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxFuncion3D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(100, 149, 237));

        jLabel4.setText("FUNCION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(100, 149, 237));

        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonEnviarFuncion.setText("ENVIAR");
        jButtonEnviarFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarFuncionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEnviarFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonEnviarFuncion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jTableListaFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableListaFunciones.setShowGrid(false);
        jScrollPane1.setViewportView(jTableListaFunciones);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jTableSalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableSalas.setShowGrid(false);
        jScrollPane3.setViewportView(jTableSalas);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jTableListaPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableListaPeliculas.setShowGrid(false);
        jScrollPane2.setViewportView(jTableListaPeliculas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(100, 149, 237));

        jTextFieldFuncionEliminar.setText("ID FUCION A ELIMINAR");
        jTextFieldFuncionEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFuncionEliminarActionPerformed(evt);
            }
        });

        jButtonEliminarFuncion.setText("ELIMINAR FUNCION");
        jButtonEliminarFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarFuncionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldFuncionEliminar)
                    .addComponent(jButtonEliminarFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldFuncionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminarFuncion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxFuncionSubtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFuncionSubtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxFuncionSubtActionPerformed

    private void jCheckBoxFuncion3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFuncion3DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxFuncion3DActionPerformed
 
    private void jButtonEnviarFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarFuncionActionPerformed
        // SALA
        String textoSala = jTextNumeroSala.getText().trim();
        if (textoSala.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de sala.");
            return;
        } 
        int nroSala= Integer.parseInt(textoSala);
        Sala sala = SalaData.buscarSala(nroSala);
        if(sala == null){
            JOptionPane.showMessageDialog(this, "La sala no existe.");
            return;
        }
        
        //PELICULA
        String tituloPelicula = jTextFuncionPelicula.getText();
        Pelicula pelicula = PeliculaData.buscarPelicula(tituloPelicula);
        if (pelicula == null) {
            JOptionPane.showMessageDialog(this, "La pelicula no existe");
            return;
        }
        
        //FECHA
        Date utilDate = jDateFechaFuncion.getDate(); //BUSCAR FECHA
        if(utilDate==null){
            JOptionPane.showMessageDialog(this, "Seleccione una fecha");
            return;
        }
        LocalDate fecha = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//SETEAR FECHA
        
        //HORA
        Date utilTime = (Date) jSpinnerHoraInicio.getValue(); //BUSCA HORA
        if(utilTime==null){
            JOptionPane.showMessageDialog(this, "Seleccione un horario");
            return;
        }
        LocalTime horaInicio= utilTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();//SETEA HORA
        LocalTime horaFin= horaInicio.plusMinutes(pelicula.getDuracion());
        
        //MEH
        String idioma = jTextFuncionIdioma.getText();
        int precio = Integer.parseInt(jTextFuncionPrecio.getText());
        boolean es3d= jCheckBoxFuncion3D.isSelected();
        boolean esSubtitulada =jCheckBoxFuncionSubt.isSelected();
        
        if(Stream.of(pelicula , sala,idioma , es3d, esSubtitulada,fecha, horaInicio,horaFin, precio).anyMatch(Objects::isNull )){
            JOptionPane.showMessageDialog(this, "❌ Asegurese de completar el formulario ❌");
            return;
        }
        
        if(es3d){
            precio*= 2;
            if(!sala.isApta3D()){
                JOptionPane.showMessageDialog(this, "❌ La sala no es apta para peliculas 3D ❌");
                return;
            }
        }
        
        Funcion f = new Funcion (pelicula , sala,idioma , es3d, esSubtitulada,fecha, horaInicio,horaFin, precio);
        
        try{
            pd.guardarFuncion(f);
            JOptionPane.showMessageDialog(this, "✅ Funcion agregada con extio ✅");

            cargarTablaFunciones();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "❌ Funcion al agregar pelicula ❌" + e.getMessage());
        }
        
        
        cargarTablaFunciones();
    }//GEN-LAST:event_jButtonEnviarFuncionActionPerformed

    private void jTextFuncionPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFuncionPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFuncionPrecioActionPerformed

    private void jTextFuncionPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFuncionPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFuncionPeliculaActionPerformed

    private void jTextNumeroSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNumeroSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNumeroSalaActionPerformed

    private void jTextFuncionIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFuncionIdiomaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFuncionIdiomaActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        jButtonVolver.addActionListener(e -> {
            vistaAdmin ventana = new vistaAdmin();
            ventana.setVisible(true);
            this.dispose(); // Cierra la ventana actual
        });
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jTextFieldFuncionEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFuncionEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFuncionEliminarActionPerformed

    private void jButtonEliminarFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarFuncionActionPerformed
        String  textoBorrar=jTextFieldFuncionEliminar.getText();
        if(textoBorrar.isBlank()){
            JOptionPane.showMessageDialog(this, "Indice el ID  de la función a eliminar.");
            return;
        }
        int borrarFuncion=Integer.parseInt(textoBorrar);
        FuncionData.borrarFuncion(borrarFuncion);
        JOptionPane.showMessageDialog(this, "Función eliminada.");
        cargarTablaFunciones();
    }//GEN-LAST:event_jButtonEliminarFuncionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new vistaFuncion().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminarFuncion;
    private javax.swing.JButton jButtonEnviarFuncion;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JCheckBox jCheckBoxFuncion3D;
    private javax.swing.JCheckBox jCheckBoxFuncionSubt;
    private com.toedter.calendar.JDateChooser jDateFechaFuncion;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerHoraInicio;
    private javax.swing.JTable jTableListaFunciones;
    private javax.swing.JTable jTableListaPeliculas;
    private javax.swing.JTable jTableSalas;
    private javax.swing.JTextField jTextFieldFuncionEliminar;
    private javax.swing.JTextField jTextFuncionIdioma;
    private javax.swing.JTextField jTextFuncionPelicula;
    private javax.swing.JTextField jTextFuncionPrecio;
    private javax.swing.JTextField jTextNumeroSala;
    // End of variables declaration//GEN-END:variables
}
