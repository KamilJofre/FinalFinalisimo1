package Vista;

import Modelo.Asiento;
import Modelo.Funcion;
import Modelo.Sala;
import Persistencia.Conexion;
import Persistencia.SalaData;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import org.mariadb.jdbc.Connection;
import java.util.logging.Logger;
import java.util.logging.Level;



public class vistaAsiento extends javax.swing.JFrame {
    
    private static final Logger logger = Logger.getLogger(vistaAsiento.class.getName());
    Connection con = (Connection) Conexion.getConexion();
    private SalaData SalaData;

    
    public void cargarSalas(){
        jComboBoxSala.removeAllItems();
        
        for(Sala s : SalaData.listarSalas()){
            jComboBoxSala.addItem(s);
        }
        System.out.println("a");
    }
    
    public void cargarFunciones(){
        jComboBoxFuncion.removeAllItems();
        
        Sala salaSeleccionada = (Sala) jComboBoxSala.getSelectedItem();
        
        if(salaSeleccionada!=null){
            for(Funcion f: SalaData.listarFuncionesDeSala(salaSeleccionada.getNroSala())){
                jComboBoxFuncion.addItem(f);
            }
        }
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
  
    public vistaAsiento() {
        initComponents();
//        placeHolder(jTextAsientoNumero, "NUMERO ASIENTO");
//        placeHolder(jTextAsientoFila, "FILA ASIENTO ");
//        
        SalaData = new SalaData(con);
        cargarSalas();
        jComboBoxSala.addActionListener(e->cargarFunciones());       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxSala = new javax.swing.JComboBox<>();
        jComboBoxFuncion = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        ResultadoFuncion2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(100, 149, 237));

        jComboBoxSala.setToolTipText("SELECCIONE SALA");
        jComboBoxSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSalaActionPerformed(evt);
            }
        });

        jComboBoxFuncion.setToolTipText("");
        jComboBoxFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFuncionActionPerformed(evt);
            }
        });

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setBackground(new java.awt.Color(0, 0, 51));
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText("PANTALLA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 196, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxSala, javax.swing.GroupLayout.Alignment.LEADING, 0, 334, Short.MAX_VALUE)
                    .addComponent(jComboBoxFuncion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(100, 149, 237));

        ResultadoFuncion2.setText("ASIENTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(ResultadoFuncion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ResultadoFuncion2)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(100, 149, 237));

        jButton1.setText("ENVIAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        jButtonVolver.addActionListener(e -> {
            vistaAdmin ventana = new vistaAdmin();
            ventana.setVisible(true);
            this.dispose(); // Cierra la ventana actual
        });
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jComboBoxSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSalaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Sala salaSeleccionada = (Sala) jComboBoxSala.getSelectedItem();
        int idSala = salaSeleccionada.getNroSala();
//        int numero = Integer.parseInt(jTextAsientoNumero.getText());
//        String fila = jTextAsientoFila.getText();
        boolean estado = true; //true=libre | false=ocupado//
        
//        Asiento a = new Asiento(fila, numero, estado, salaSeleccionada);
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFuncionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFuncionActionPerformed

    
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
        java.awt.EventQueue.invokeLater(() -> new vistaAsiento().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ResultadoFuncion2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<Funcion> jComboBoxFuncion;
    private javax.swing.JComboBox<Sala> jComboBoxSala;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables
}
