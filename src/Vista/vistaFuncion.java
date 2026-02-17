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
        
        SpinnerDateModel modeloHora= new SpinnerDateModel();
        jSpinnerHoraInicio.setModel(modeloHora);
        
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpinnerHoraInicio,"HH:mm");
        jSpinnerHoraInicio.setEditor(editor);
    }
    
    private void cargarTablaSalas() {
    DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"Nro Sala", "Capacidad", "3D", "Estado"}, 0
        );
        jTableSalas.setModel(modelo);

        for (Sala s : SalaData.listarSalas()) {
            modelo.addRow(new Object[]{
                s.getNroSala(),
                s.getCapacidad(),
                s.isApta3D(),
                s.isEstado()
            });
        }
    }
    
    private void cargarTablaFunciones() {
    DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"Id Funcion","Numero Sala", "Pelicula", "Idioma", "Fecha","Hora Inicio","Precio"}, 0
        );
        jTableListaFunciones.setModel(modelo);

        for (Funcion f : FuncionData.listarFunciones()) {
            modelo.addRow(new Object[]{
                f.getIdFuncion(),
                f.getSala().getNroSala(),
                f.getPelicula().getTitulo(),
                f.getIdioma(),
                f.getFechaFuncion(),
                f.getHoraInicio(),
                f.getPrecio()
            });
        }
    }
    
    void cargarTablaPeliculas() {
    DefaultTableModel modelo = new DefaultTableModel(
    new Object[]{"Id Película","Titulo", "Director", "Origen", "Genero"}, 0
        );
        jTableListaPeliculas.setModel(modelo);

        for (Pelicula p : PeliculaData.listarPeliculas()) {
            modelo.addRow(new Object[]{
                p.getIdPelicula(),
                p.getTitulo(),
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaFunciones = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableListaPeliculas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableSalas = new javax.swing.JTable();

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextFuncionIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFuncionPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDateFechaFuncion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jCheckBoxFuncion3D, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFuncionPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSpinnerHoraInicio, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBoxFuncionSubt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jTextNumeroSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextNumeroSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFuncionPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFuncionIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFuncionPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxFuncionSubt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxFuncion3D)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(100, 149, 237));

        jLabel4.setText("FUNCION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jScrollPane1.setViewportView(jTableListaFunciones);

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
        jScrollPane2.setViewportView(jTableListaPeliculas);

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
        jScrollPane3.setViewportView(jTableSalas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        int nroSala = Integer.parseInt(jTextNumeroSala.getText());
        Sala sala = SalaData.buscarSala(nroSala);
        if (sala == null) {
            JOptionPane.showMessageDialog(this, "La sala no existe");
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
        }LocalDate fecha = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//SETEAR FECHA
        
        //HORA
        Date utilTime = (Date) jSpinnerHoraInicio.getValue(); //BUSCA HORA
        if(utilTime==null){
            JOptionPane.showMessageDialog(this, "Seleccione un horario");
        }LocalTime horaInicio= utilTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();//SETEA HORA
        
        
        //MEH
        String idioma = jTextFuncionIdioma.getText();
        int precio = Integer.parseInt(jTextFuncionPrecio.getText());
        boolean es3d= jCheckBoxFuncion3D.isSelected();
        boolean esSubtitulada =jCheckBoxFuncionSubt.isSelected();
        
        if(es3d){precio+= 500;}
        
        Funcion f = new Funcion (pelicula , sala,idioma , es3d, esSubtitulada,fecha, horaInicio, precio);
        
        try{
            pd.guardarFuncion(f);
            JOptionPane.showMessageDialog(this, "Funcion agregada con extio ✅");
            
            cargarTablaFunciones();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Funcion al agregar pelicula ❌" + e.getMessage());
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
    private javax.swing.JButton jButtonEnviarFuncion;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JCheckBox jCheckBoxFuncion3D;
    private javax.swing.JCheckBox jCheckBoxFuncionSubt;
    private com.toedter.calendar.JDateChooser jDateFechaFuncion;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerHoraInicio;
    private javax.swing.JTable jTableListaFunciones;
    private javax.swing.JTable jTableListaPeliculas;
    private javax.swing.JTable jTableSalas;
    private javax.swing.JTextField jTextFuncionIdioma;
    private javax.swing.JTextField jTextFuncionPelicula;
    private javax.swing.JTextField jTextFuncionPrecio;
    private javax.swing.JTextField jTextNumeroSala;
    // End of variables declaration//GEN-END:variables
}
